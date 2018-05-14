*******
Graylog
*******

Indexing
========

If a message is written to one index, is it also written to other indexes also?
-------------------------------------------------------------------------------

Graylog routes every message into the ``All Messages`` stream by default. This
places those messages into the `default index set` and any other stream/index
set where a match occurs.

`From the docs:
<http://docs.graylog.org/en/latest/pages/streams.html#index-sets>`_

   **Index sets**

   Graylog routes every message into the All messages stream by default, unless
   the message is removed from this stream with a pipeline rule (see Processing
   Pipelines) or it's routed into a stream marked with Remove matches from
   ``All messages`` stream.

   The latter is useful if messages should be stored with different settings
   than the ones in the Default index set, for example web server access logs
   should only be stored for 4 weeks while all other messages should be stored
   for 1 year.

   **Storage requirements**

   Graylog writes messages once for each index set into Elasticsearch. This
   means that if all streams are using the Default index set, each message
   will be written exactly once into Elasticsearch, no matter into how many
   streams the message has been sent. This can be thought of a kind of
   de-duplication.

   If some streams use other index sets and the Remove matches from
   ``All messages`` stream setting is not enabled, messages will be written
   into Elasticsearch at least twice, once for the Default index set and once
   for the assigned index set. This means that the same message will be stored
   in two or more indices in Elasticsearch with different index settings.

   Unless you explicitly want to store messages multiple times in different
   Elasticsearch indices, either assign the Default index set to the respective
   streams or enable the Remove matches from ``All messages`` stream setting
   for the respective streams.
