# PostgreSQL: Databases

## Show databases

```sql
\l
```

## Create database

```sql
CREATE DATABASE example;
```

## Create tables

```sql
CREATE TABLE articles(id SERIAL PRIMARY KEY, title VARCHAR(255), body TEXT);
CREATE TABLE categories(id SERIAL PRIMARY KEY, name VARCHAR(255));
ALTER TABLE articles ADD COLUMN category_id INT;
ALTER TABLE articles ADD CONSTRAINT catfk FOREIGN KEY(category_id) REFERENCES categories(id);
```

TODO: How to specify the `CONSTRAINT` at the time of table creation?

## References

- <https://www.packtpub.com/application-development/full-stack-web-development-video>
