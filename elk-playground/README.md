Scenario: As as developer I wanted to setup monitoring (Analyze springboot logs using ELK stack.), CI/CD for springboot application.


```
ELK Stands for ElasticSearch, Logstash and Kibana
Some elk related images will come here..
```

Installations:

```
ElasticSearch:


$ wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.0.0.zip
$ unzip elasticsearch-7.0.0.zip
$ cd elasticsearch-7.0.0
$ bin/elasticsearch


$ curl http://localhost:9200
```

```

Kibana:

$ wget https://artifacts.elastic.co/downloads/kibana/kibana-5.1.1-darwin-x86_64.tar.gz
$ tar xvzf kibana-5.1.1-darwin-x86_64.tar.gz
$ cd kibana-5.1.1-darwin-x86_64
$ bin/kibana

http://localhost:5601

```

```
Logstash:

$ wget https://artifacts.elastic.co/downloads/logstash/logstash-7.0.0.zip
$ unzip logstash-7.0.0.zip
$ vim logstash.conf

Configuration:


     input {
      file {
        type => "java"
        path => "/var/logs/springboot-app-elk.log"
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
      }
    }

$ bin/logstash -f logstash.conf

```

```
What is the difference between Logstash and Beats?
More content will come here ...
```


```
What is ElasticSearch ?

Elastic search is full distributed nosql database (enterprise search) and analytics engine.
It is built on the Apache Lucene search engine library and exposes data through REST and Java APIs.
Elasticsearch is scalable and is built to be used by distributed systems.

Basic Concepts of ElasticSearch:

Cluster
Node
Shard
Replicas
Index
Documents
Mapping
Schema

More content will come here ...

```

```
What is logstash ?
More content will come here ...
```

```
What is kibana ?
More content will come here ...
```


```
To see the graph paste the following code on url (https://dreampuf.github.io/GraphvizOnline). This convert code into graph.


# comments start with either # or //
// take your pick :D
// ### using three #'s might help things line up visually
// ### everything must be in a digraph{}

digraph SimplestDiagrams {
    "Redis" [
        shape=record
    ]

    "ElasticSearch" [
        shape=record
    ]

    "SpringBootApp" [
        shape=record
        label="{SpringBootApp}"
    ]

    "Browser" [
        shape=record
        label="{Browser}"
    ]


    SpringBootApp -> LogStashShipper [label=" Read from spring boot log file" arrowhead=crow]
    LogStashShipper -> Redis [label=" Push to Redis"]
    Redis -> LogStashIndexer [label=" Pull from Redis" arrowhead=crow]
    LogStashIndexer -> ElasticSearch [label=" Push to ElasticSearch"]
    ElasticSearch -> Kibana [label= "Pull from ElasticSearch" arrowhead=crow]
    Kibana -> Browser [label=" Query on Kibana" arrowhead=crow]
}


```
