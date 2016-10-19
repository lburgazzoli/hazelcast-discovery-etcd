#!/usr/bin/env bash

HOST_IP="127.0.0.1"
ETCD_NAME="hz-discovery"

./etcd-dist/etcd \
    -debug \
    -name $ETCD_NAME \
    --data-dir ./etcd-dist/$ETCD_NAME \
    -advertise-client-urls http://${HOST_IP}:2379,http://${HOST_IP}:4001 \
    -listen-client-urls http://0.0.0.0:2379,http://0.0.0.0:4001 \
    -initial-advertise-peer-urls http://${HOST_IP}:2380 \
    -listen-peer-urls http://0.0.0.0:2380 &



sleep 5

./etcd-dist/etcdctl set /hazelcast/node1 '{ "host": "127.0.0.1", "port": 9001 , "tags": { "name": "node1" } }'
./etcd-dist/etcdctl set /hazelcast/node2 '{ "host": "127.0.0.1", "port": 9002 , "tags": { "name": "node2" } }'
./etcd-dist/etcdctl set /hazelcast/node3 '{ "host": "127.0.0.1", "port": 5701 , "tags": { "name": "node3" } }'
./etcd-dist/etcdctl set /hazelcast/node4 '{ "host": "127.0.0.1" , "tags": { "name": "node4" } }'

