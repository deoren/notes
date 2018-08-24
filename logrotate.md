# Logrotate notes

## Logrotate will only remove old logs that you tell it to

What commonly trips me up is when I *reduce* the number of log files that
logrotate is monitoring. I will take a rotation period of say 30 days and
drop it to 14 (for example) and will be surprised when older files are left
behind.

According to [johnshen64](https://serverfault.com/users/47260/johnshen64) from
serverfault.com, this is because when you say `rotate 2`, you are telling
logrotate to *only* handle rotation of 2 files, not also handle purging the
older files.

His post didn't cover it, but apparently the `maxage` setting will not assist
with this specific issue.

## Purge old files

This is needed if you adjust your logrotate config to rotate *fewer* files
than it previously handled.

- `find /var/log/apache2 -maxdepth 1 -mtime +<n> -name "*.log.*.gz" -delete`
  - `n` is the number of days. For example, if your conf file handles
    90 days, then using `90` here will get rid of all of the files that are
    no longer being handled.

## References

- <https://serverfault.com/questions/636844/making-logrotate-remove-old-logs-after-reducing-rotate-value>
- <https://serverfault.com/questions/377031/logrotate-does-not-remove-old-logs>
