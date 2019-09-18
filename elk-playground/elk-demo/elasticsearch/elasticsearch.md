# Elasticsearch: updating the mappings and settings of an existing index


[Elasticsearch](http://www.elasticsearch.org/) will automatically create an index (with basic settings and mappings) for you if you post a first document:

```bash
$ curl -X POST 'http://localhost:9200/thegame/weapons/1' -d \
'{
  "_id": 1,
  "name": "Longsword",
  "description": "The Longsword can be wielded in one or two hands and is also known as a Bastard Sword. It combines power, speed and reach to become one of the most well rounded weapons.",
  "category": "Sharp"
}'
```

You can see the settings (for the index) and mapping (for the `weapons` type) with:

```bash
$ curl -X GET 'http://localhost:9200/thegame/_settings'
$ curl -X GET 'http://localhost:9200/thegame/weapons/_mapping'
```

To **update the settings**, if you're defining new analyzers or filters, you first need to `_close` the index, then `_open` it when done updating:

```bash
$ curl -X POST 'http://localhost:9200/thegame/_close'

$ curl -X PUT 'http://localhost:9200/thegame/_settings' -d \
'{
  "analysis": {
    "analyzer": {
      "full_name": {
        "filter": [
          "standard",
          "lowercase",
          "asciifolding"
        ],
          "type": "custom",
          "tokenizer": "standard"
      }
    }
  }
}'

$ curl -X POST 'http://localhost:9200/thegame/_open'
```

To **update the mappings** of this existing index, you need to do it for each type (here we only have the `weapons` type):

```bash
$ curl -X PUT 'http://localhost:9200/thegame/weapons/_mapping?ignore_conflicts=true' -d \
'{
  "weapons": {
    "properties": {
      "name": {
        "type": "string",
        "analyzer": "full_name"
      },
      "description": {
        "type": "string"
      },
      "category": {
        "type": "string"
      }
    }
  }
}'
```

You can do all of this at once if you delete then re-create your index, but you will **loose all stored documents**, so you will have to reload them. But sometimes for big changes to mapping/settings, this makes more sense:

```bash
$ curl -X DELETE 'http://localhost:9200/thegame'
$ curl -X POST 'http://localhost:9200/thegame' -d \
'{
  "mappings": {
    "weapons": {
      "properties": {
        "name": {
          "type": "string",
          "analyzer": "full_name"
        },
        "description": {
          "type": "string"
        },
        "category": {
          "type": "string"
        }
      }
    }
  },
  "settings": {
    "analysis": {
      "analyzer": {
        "full_name": {
          "filter": [
            "standard",
            "lowercase",
            "asciifolding"
          ],
            "type": "custom",
            "tokenizer": "standard"
        }
      }
    }
  }
}'
```
