# PostgreSQL: Users

## Create new user

```sql
CREATE USER devuser WITH PASSWORD '12345';

```

## Show user accounts

```sql
SELECT * FROM pg_user;
```

## Alter or Grant additional privileges

### Grant `superuser` privileges

```sql
ALTER USER devuser WITH SUPERUSER;
```

## Change password for user

```sql
UPDATE pg_shadow SET passwd = 'newpassword' WHERE username = 'devuser';
```

## Grant full privileges to a database

```sql
GRANT ALL ON DATABASE example TO devuser;
```

## References

- <https://www.packtpub.com/application-development/full-stack-web-development-video>
