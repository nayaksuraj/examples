##### Scenario: As as developer I wanted to setup logging for my app.



###### Elastic-Stack Overview
```
Elasticsearch: a NoSQL database based on the Lucene search engine.
Logstash: a server-side data processing pipeline that accepts data from various simultaneously, transforms it, and exports the data to various targets.
Kibana: a visualization layer that works on top of Elasticsearch.
```

#### Requirements
```
You have an instance with at least 4GB of RAM
java 1.8
```

#### Installations Steps:

**1. Install Elasticsearch**


    $ wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.3.2-linux-x86_64.tar.gz
    $ tar -zxvf elasticsearch-7.3.2-linux-x86_64.tar.gz
    $ cd elasticsearch-7.3.2
    $ ./bin/elasticsearch &



**2. Verify Elastic Search if it's running**


    $ curl http://localhost:9200


    # You should get similar to following data.

    vagrant ➜  elasticsearch-7.3.2  curl http://localhost:9200
    {
      "name" : "ubuntu-xenial",
      "cluster_name" : "elasticsearch",
      "cluster_uuid" : "X_R8xxCDSfSsTXW2OqpyAw",
      "version" : {
        "number" : "7.3.2",
        "build_flavor" : "default",
        "build_type" : "tar",
        "build_hash" : "1c1faf1",
        "build_date" : "2019-09-06T14:40:30.409026Z",
        "build_snapshot" : false,
        "lucene_version" : "8.1.0",
        "minimum_wire_compatibility_version" : "6.8.0",
        "minimum_index_compatibility_version" : "6.0.0-beta1"
      },
      "tagline" : "You Know, for Search"
    }


**3. Install Kibana**


    Kibana:

    $ wget https://artifacts.elastic.co/downloads/kibana/kibana-7.3.2-linux-x86_64.tar.gz
    $ tar xvzf kibana-7.3.2-linux-x86_64.tar.gz
    $ cd kibana-7.3.2-linux-x86_64


    # Open the file config/kibana.yml and uncomment the following lines.

        server.port: 5601
        elasticsearch.hosts: ["http://localhost:9200"]
        server.host: "0.0.0.0"

    $ ./bin/kibana &

    # Give Kibana a few seconds to initialize, then access the Kibana web UI by hitting
    # [http://localhost:5601](http://localhost:5601) with a web browser.



**4. Install Logstash**


    Logstash:

    $ wget https://artifacts.elastic.co/downloads/logstash/logstash-7.3.2.tar.gz
    $ tar -zxvf logstash-7.3.2.tar.gz
    $ cd logstash-7.3.2



    # open config/logstash.conf and following configuration

    Configuration:


         input {
          file {
            type => "java"
            path => "/home/vagrant/elk/examples/elk-playground/springbootelkdemo/spring-boot-elk.log"
            codec => multiline {
              pattern => "^%{YEAR}-%{MONTHNUM}-%{MONTHDAY} %{TIME}.*"
              negate => "true"
              what => "previous"
            }
          }
        }

        filter {
          #If log line contains tab character followed by 'at' then we will tag that entry as stacktrace
          if [message] =~ "\tat" {
            grok {
              match => ["message", "^(\tat)"]
              add_tag => ["stacktrace"]
            }
          }
        }

        output {
          stdout {
            codec => rubydebug
          }
          # Sending properly parsed log events to elasticsearch
          elasticsearch {
            hosts => ["elasticsearch:9200"]
            index => "logstash-app1-%{+YYYY.MM.dd}"
          }
        }


    # start the logstash server
    $ bin/logstash -f logstash.conf



###### ELK Endpoints:
```
ElasticSearch: localhost:9200
Kibana: localhost:5601
Logstash: localhost:5044

```
