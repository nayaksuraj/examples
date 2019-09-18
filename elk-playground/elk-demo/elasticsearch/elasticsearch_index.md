# To get a list of all available indices in your elasticsearch, use the following URL
curl -XGET http://localhost:9200/_cat/indices

# create index
$ curl -X PUT http://localhost:9200/marutifactory

# To get the status of an index (say, marutifactory), use the following URL:
$ curl -XGET http://localhost:9200/marutifactory?pretty

# ingesting data 
curl -X POST -H 'Content-Type: application/json' 'http://localhost:9200/marutifactory/cars/1' -d \
'{
  "name": "Baleno",
  "description": "This is new variant",
  "dealer": "nexa"
}'

# ingesting data without id (es will create id for you)
curl -X POST -H 'Content-Type: application/json' 'http://localhost:9200/marutifactory/cars' -d \
'{
  "name": "Baleno",
  "description": "This is new variant",
  "dealer": "nexa"
}'
