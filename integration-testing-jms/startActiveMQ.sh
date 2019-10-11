#!/usr/bin/env bash

docker stop demo-activemq
docker rm demo-activemq


docker run --name='demo-activemq' -d \
-e 'ACTIVEMQ_CONFIG_NAME=amqp-srv1' \
-e 'ACTIVEMQ_CONFIG_DEFAULTACCOUNT=false' \
-e 'ACTIVEMQ_ADMIN_LOGIN=admin' \
-e 'ACTIVEMQ_ADMIN_PASSWORD=secret' \
-p 8161:8161 \
-p 61616:61616 \
-p 61613:61613 \
webcenter/activemq:5.14.3