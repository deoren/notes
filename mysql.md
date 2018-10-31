# MySQL / MariaDB

## Limits

### Database

- 64 characters for database name

- Field comments
  - 255 characters < MySQL 5.5.3
  - 1024 characters >= MySQL 5.5.3

- Table comments
  - 60 characters before MySQL 5.5.3
  - 2048 characters after MySQL 5.5.3

### Users

- username
  - 16 characters <= MySQL 5.6
  - 32 characters >= MySQL 5.7

- password
  - 32 characters < MySQL 5.7
  - Specific to MySQL replication slave account
    - Resolved in MySQL 5.7 (Bug #11752299, Bug #43439)

## References

- <https://dev.mysql.com/doc/refman/5.6/en/user-names.html>
- <https://stackoverflow.com/questions/7465204/maximum-mysql-user-password-length>

- <https://wiki.whyaskwhy.org/MySQL>
  - migrating notes away from this wiki as time allows
