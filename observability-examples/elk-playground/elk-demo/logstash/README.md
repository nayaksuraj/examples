```
What is logstash ?
More content will come here ...
```


Docker:
```
  $ docker build -t logstash:7.3.2 .
  $ docker run -d -p 5400:5400 \
  -v "$(pwd)"/config/logstash.yml:/usr/share/logstash/config/logstash.yml \
  -v "$(pwd)"/pipeline/:/usr/share/logstash/pipeline/ \
  -v /var/log/spring-boot-elk.log:/tmp/spring-boot-elk.log \
  logstash:7.3.2
```


Notes:
  1. Please update your springboot log file before you start logstash docker container.
