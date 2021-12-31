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
#save number of CPU to a variable
lscpu_out=`lscpu`

# obtain hardware info
hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "Model:" | awk '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep 'CPU\sMHz:' | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "L2\scache:" | awk '{print $3}' | xargs)
total_mem=$(echo "$mem_info"  | egrep "MemTotal:" | awk '{print $2}' | xargs)
timestamp= $(date +"%Y-%m-%d %H:%M:%S")

# Insert statement
insert_stmt="INSERT INTO PUBLIC.host_info
(
  hostname,
  cpu_number,
  cpu_architecture,
  cpu_model,
  cpu_mhz,
  l2_cache,
  total_mem,
  timestamp
)
  VALUES
(
  '$hostname',
  '$cpu_number',
  '$cpu_architecture',
  '$cpu_model',
  '$cpu_mhz',
  '$l2_cache',
  '$total_mem',
  '$timestamp'
)"

# Use environment variable PGPASSWORD to connect to psql instance w/o prompting for password
export PGPASSWORD=$psql_password

# Insert
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?