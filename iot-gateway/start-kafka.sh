#!/usr/bin/env bash
docker run -d --name zookeeper -p 2181:2181 confluent/zookeeper
docker run -d --name kafka -p 9092:9092 --link zookeeper:zookeeper \
    --env KAFKA_ADVERTISED_HOST_NAME=localhost confluent/kafka

