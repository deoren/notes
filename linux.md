# Linux - Misc notes for various distros that I use

## Table of contents

- [Linux - Misc notes for various distros that I use](#linux---misc-notes-for-various-distros-that-i-use)
  - [Table of contents](#table-of-contents)
  - [systemd](#systemd)
    - [unit files](#unit-files)
      - [override unit file](#override-unit-file)
      - [revert unit file overrides](#revert-unit-file-overrides)
    - [journalctl](#journalctl)
      - [Show kernel log messages with an ERROR priority](#show-kernel-log-messages-with-an-error-priority)
      - [Show log messages with an INFO priority since the last boot](#show-log-messages-with-an-info-priority-since-the-last-boot)
      - [Show ALL log messages between a time range](#show-all-log-messages-between-a-time-range)
      - [Force time (shown in local time by default) to be displayed in UTC](#force-time-shown-in-local-time-by-default-to-be-displayed-in-utc)
      - [List messages for a specific systemd unit (e.g., `rsyslog`)](#list-messages-for-a-specific-systemd-unit-eg-rsyslog)
      - ["Tail" messages for a specific systemd unit (e.g., `rsyslog`)](#%22tail%22-messages-for-a-specific-systemd-unit-eg-rsyslog)
      - [Show messages from most recent boot](#show-messages-from-most-recent-boot)
  - [Ubuntu](#ubuntu)
    - [Environment variables](#environment-variables)
  - [References](#references)

## systemd

### unit files

#### override unit file

`systemctl edit rsyslog.service`

This creates a `/etc/systemd/system/rsyslog.service.d/override.conf` file with
settings enterered via the interactive editor that is opened (likely
determined via `$EDITOR` environment variable).

You can also create multiple "drop-in" files (aka, "drop-ins") manually to
override specific settings.

#### revert unit file overrides

`sudo systemctl revert`

As noted by Stephen Kitt (StackExchange member):

> This reverts the given unit to its vendor configuration, deleting all
> overrides. (It will also restore the unit's properties to their defaults,
> and unmask it if it was masked by the administrator.)

### journalctl

#### Show kernel log messages with an ERROR priority

`sudo journalctl -p err -k`

#### Show log messages with an INFO priority since the last boot

`sudo journalctl -p info -b`

#### Show ALL log messages between a time range

 `sudo journalctl --since "2018-07-15 15:00:00" --until "2018-07-15 16:00:00"`

The `--since` and `--until` options can be used together (as shown here)
or separately as needed for the desired effect.

#### Force time (shown in local time by default) to be displayed in UTC

`sudo journalctl --utc`

#### List messages for a specific systemd unit (e.g., `rsyslog`)

`sudo journalctl -u rsyslog.service`

#### "Tail" messages for a specific systemd unit (e.g., `rsyslog`)

`sudo journalctl -f -u rsyslog.service`

#### Show messages from most recent boot

Specific service:

`sudo journalctl -b -u nginx.service`

Everything:

`sudo journalctl -b`

## Ubuntu

### Environment variables

- `/etc/environment`
  - Common/central location for setting environment variables
  - Example: ```ES_HEAP_SIZE="512M"```

## References

- [Pro Linux System Administration](https://www.apress.com/us/book/9781484220078)
- <https://askubuntu.com/questions/995711/where-can-i-find-the-boot-log>
- <https://unix.stackexchange.com/questions/398540/how-to-override-systemd-unit-file-settings>
- <https://unix.stackexchange.com/questions/449051/systemd-delete-overrides>
