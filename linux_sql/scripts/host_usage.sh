#!/bin/bash

# input arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# check # of arguments
if [ $# -ne 5 ]; then
  echo 'Illegal number of arguments'
  exit f1
fi

# save hostname to a variable
hostname=$(hostname -f)
# save machine statistics in MB
vmstat_mb=$(vmstat --unit M)

# Obtain Usage Info
timestamp=$(date +"%Y-%m-%d %H:%M:%S")
host_id="(SELECT id FROM host_info WHERE hostname='$hostname')"
memory_free=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(echo "$vmstat_mb" | awk '{print $15}'| tail -n1 | xargs)
cpu_kernel=$(echo "$vmstat_mb" | awk '{print $14}'| tail -n1 | xargs)
disk_io=$(vmstat -d | awk '{ print $10 }' | tail -n 1)
disk_available=$(df -BM / | awk '{ print $4 }' | tail -n 1)

# Insert statement
insert_stmt="INSERT INTO PUBLIC.host_usage
(
  timestamp,
  host_id,
  memory_free,
  cpu_idle,
  cpu_kernel,
  disk_io
  disk_available
)
  VALUES
(
  '$timestamp',
  '$host_id',
  '$memory_free',
  '$cpu_idle',
  '$cpu_kernel',
  '$disk_io',
  '$disk_available'
)"

# Use environment variable PGPASSWORD to connect to psql instance w/o prompting for password
export PGPASSWORD=$psql_password

# Insert
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?