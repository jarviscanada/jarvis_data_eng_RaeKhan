#!/bin/bash

# enter 3 CLI arguments
cmd=$1
db_username=$2
db_password=$3

# Obtain status or start docker
sudo systemctl status docker || sudo systemctl start docker

# display detailed info regarding container, set variable container_status to exit status then
docker container inspect jrvs-psql
container_status=$?

# Use case for conditional statements
case $cmd in

  create)
    # if the container status is 0, the container already exists
    if [ $container_status -eq 0 ]; then
      echo 'Container already exists'
      exit 1
    fi

    # Ensure all 3 CLI arguments are entered
    if [ $# -ne 3 ]; then
      echo 'Create requires username and password'
      exit 1
    fi

    # create new volume, along with container using psql image
    docker volume create pgdata
    docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
    exit $?
    ;;

  start|stop)
    # if the container status is not 0, then it is not created
    if [ $container_status -ne 0 ]; then
      echo 'Container not created'
      exit 1
    fi

    # start or stop the container
    docker container $cmd jrvs-psql
    exit $?
    ;;

  *)
    echo 'Illegal command'
    echo 'Commands: start|stop|create'
    exit 1
    ;;
  esac