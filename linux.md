# Linux - Misc notes for various distros that I use

## systemd

### journalctl

- Show kernel log messages with an ERROR priority
  - `sudo journalctl -p err -k`
- Show log messages with an INFO priority since the last boot
  - `sudo journalctl -p info -b`
- Show ALL log messages between a time range
  - `sudo journalctl --since "2018-07-15 15:00:00" --until "2018-07-15 16:00:00"`
  - The `--since` and `--until` options can be used together (as shown here)
      or separately as needed for the desired effect.
- Force time (shown in local time by default) to be displayed in UTC
  - `sudo journalctl --utc`
- List messages for a specific systemd unit (e.g., `rsyslog`)
  - `sudo journalctl -u rsyslog.service`
- "Tail" messages for a specific systemd unit (e.g., `rsyslog`)
  - `sudo journalctl -f -u rsyslog.service`

## Ubuntu

### Environment variables

- `/etc/environment`
  - Common/central location for setting environment variables
  - Example: ```ES_HEAP_SIZE="512M"```

## References

- [Pro Linux System Administration](https://www.apress.com/us/book/9781484220078)
