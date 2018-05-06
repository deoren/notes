# Elasticsearch

Various notes I've jotted down while studying Elasticsearch. At some point I
may port these notes to reST format for long-term maintenance.

## Basics

### Data representation

The data model for Elasticsearch has been historically described by Elastic.co
as being similar to a Relational Database:

Relational Database | Elasticsearch
------------------- | -------------
Database | Index
Table | Type
Row | Document
Column | Field

With later relases of Elasticsearch however, types are being phased out. See
the Types section below for details.


#### Index

- Contains one or more documents

##### Shards

Technically speaking, a shard is an instance of Apache Lucene. From the
Elasticsearch point of view, a shard is a portion of an index.

##### Primary shards

A certain number of primary shards are assigned when an index is created. By
default this is 5. This is fixed (immutable) at the time of creation (e.g.,
it cannot be changed later).

It is beneficial (to preserve resources) to set the Primary Shards
(count) to 1 if using a single node cluster (e.g., for development/testing
purposes).

##### Replica shards

Each Replica shard is a copy of the Primary shard.

The number of Replica shards can be changed at any time after the index
has been created. By default, one Replica shard is assigned per index.

### Type

- Representation of a class of similar documents.
- Concept of mapping (describe the field properties of a given type)

The example given in "Documents in Elasticsearch" is:

```
Bank -> index
Customers -> type
Customer -> document
    FIRST_NAME -> field -> "String"
    LAST_NAME -> field -> "String"
    ACCOUNT -> field -> "Int"
```

#### Deprecation of types

From an official Elastic blog post:

> User expectations: This is probably the most prominent issue and Elastic is
> unfortunately at least partially to blame: once a bad analogy is out there,
> it is nearly impossible to kill.  **At some points in the past, we have stated
> that Elasticsearch indices were like traditional RDBMS databases while
> Elasticsearch types were similar to tables.  However this was really an
> oversimplification of reality and as a result many people have the mental
> model that types are equivalent to tables in the relational world.  In
> reality,in Elasticsearch, the underlying data structures are the same for
> the entire index, not per type.**  Due to the misconceptions, one of the most
> common pitfalls we've seen is that users expect fields to be independent
> across types. However, they must be of the same field type. So
> `/my-index/type-a/my-field` must use the same data type as
> `/my-index/type-b/my-field`.

Older indexes can be reindexed to move data out of the `_type` field into
another field (e.g., `type`).

- ES 5.6: Users can set `mapping.single_type` to true to opt-in to limiting types
 to one per index
- ES 6: one type per index
- ES 7: expected to remove types entirely

### Document

- Individual entry in Elasticsearch that is the primary unit of adding data.

### Field

- stub section

## Clustering

### High availability & fault tolerance

- Dedicated masters is a must
    - `discovery.zen.minimum_master_nodes` = N/2 + 1
       (e.g., 50% + 1, so for 5 master eligible nodes, set 3)

- Keep incides balanced
    - review the `total_shards_per_node` option
    - size-based indices vs time-based indices, otherwise hotspots
      will be an issue.

## Queries

> Documents in Elasticsearch are immutable; we cannot change them. Instead, if
> we need to update an existing document, we reindex or replace it using the
> index API.

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

- [Learning ElasticSearch 5.0 [Video]](https://www.packtpub.com/big-data-and-business-intelligence/learning-elasticsearch-50-video)

- https://www.elastic.co/guide/en/elasticsearch/guide/current/_talking_to_elasticsearch.html
- https://www.elastic.co/guide/en/elasticsearch/guide/current/index-doc.html
- https://www.elastic.co/guide/en/elasticsearch/guide/current/doc-exists.html
- https://www.elastic.co/guide/en/elasticsearch/guide/current/create-doc.html
- https://www.elastic.co/guide/en/elasticsearch/guide/current/delete-doc.html

- Types are being phased out
    - https://www.elastic.co/blog/index-type-parent-child-join-now-future-in-elasticsearch
    - https://www.elastic.co/blog/a-practical-introduction-to-elasticsearch
    - https://github.com/elastic/elasticsearch/issues/20257
