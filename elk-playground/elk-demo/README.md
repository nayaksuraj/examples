# Dockers-ELK

1. Start the ELK stack using `docker-compose`:

```console
$ docker-compose up
```
# OR

You can also choose to run it in background (detached mode):

```console
$ docker-compose up -d


Give Kibana a few seconds to initialize, then access the Kibana web UI by hitting
[http://localhost:5601](http://localhost:5601) with a web browser.

By default, the stack exposes the following ports:
* 5000: Logstash TCP input.
* 9200: Elasticsearch HTTP
* 9300: Elasticsearch TCP transport
* 5601: Kibana
