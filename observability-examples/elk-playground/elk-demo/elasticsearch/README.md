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

Docker:
```
  $ docker build -t elasticsearch:7.3.2 .
  $ docker run -d -p 9200:9200 elasticsearch:7.3.2
```
