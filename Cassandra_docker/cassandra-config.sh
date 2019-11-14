#!/bin/bash
# Stop service before changing config
service cassandra stop;

echo test args "$@"

# Setting to vars value of args
NODE1=$1
NODE2=$2
NODE3=$3
me=$NODE1

echo my address is $me

# Append to config file of cassandra 
printf "cluster_name: 'Test Cluster'
num_tokens: 256
seed_provider:
    - class_name: org.apache.cassandra.locator.SimpleSeedProvider
      parameters:
          - seeds: $NODE1,$NODE2,$NODE3
listen_address: $me
rpc_address: $me
endpoint_snitch: GossipingPropertyFileSnitch\n" >> /etc/cassandra/cassandra.yaml;

# I dont know why to be removed this file ### todo learn it for what will be used
rm /etc/cassandra/cassandra-topology.properties

# cat /etc/cassandra/cassandra.yaml
# Checking cassandra status
service cassandra status;

# Run cassandra 
cassandra -f -R;
# sleep 1m
# service cassandra status;
 
# nodetool status;
