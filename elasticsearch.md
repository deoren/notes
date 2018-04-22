# Elasticsearch

## Basics

### Data representation

Elasticsearch doesn't follow the traditional model of databases, tables and
rows, but it helps to use a familiar data model to undestand how Elasticsearch
organizes the data that it manages.

Relational Database | Elasticsearch
------------------- | -------------
Database | Index
Table | Type
Row | Document
Column | Field
#### Index

- Contains one or more documents

##### Shards

Technically speaking, a shard is an instance of Apache Lucene. From the
Elasticsearch point of view, a shard is a portion of an index.

### Type

- stub section

### Document

- stub section

### Field

- stub section

## Clustering

- stub section

## Queries

> Documents in Elasticsearch are immutable; we cannot change them. Instead, if
> we need to update an existing document, we reindex or replace it, which we can
> do using the same index API that we have already discussed in Indexing a
> Document.

### Types of queries

HTTP Method/Verb | Purpose
--------- | -------
GET | Retrieve a document
PUT | *store this document at this URL*
POST | create a new document, *store this document **under** this URL* (auto-generate ids)
HEAD | Check whether a document exists (only headers are returned)
DELETE | Delete a document


### Curl examples

Task | `curl` command
------------ | -------------
Add index | `curl -XPUT http://localhost:9200/INDEX_NAME`
Get all indices in a cluster | `curl -XGET http://localhost:9200/_cat/indices?v`
Get a specific index in a cluster | `curl -XGET http://localhost:9200/INDEX_NAME`


## References

- http://www.elasticsearchtutorial.com/basic-elasticsearch-concepts.html
- http://www.elasticsearchtutorial.com/elasticsearch-in-5-minutes.html

- https://stackoverflow.com/questions/630453/put-vs-post-in-rest

- https://www.elastic.co/guide/en/elasticsearch/guide/current/_talking_to_elasticsearch.html
- https://www.elastic.co/guide/en/elasticsearch/guide/current/index-doc.html
- https://www.elastic.co/guide/en/elasticsearch/guide/current/doc-exists.html
- https://www.elastic.co/guide/en/elasticsearch/guide/current/create-doc.html
- https://www.elastic.co/guide/en/elasticsearch/guide/current/delete-doc.html
