# PostgreSQL: Gotchas

## FATAL: database devuser does not exist

If not specified, `psql` expects that a database of the same name of the user logging in exists. If that database does not exist, specify the database that you wish to work with at the time of login.

```shell
psql -U devuser example
```

## References

- <https://www.packtpub.com/application-development/full-stack-web-development-video>
