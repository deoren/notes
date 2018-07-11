# Python: Logging

## Observations

### Paramiko module

#### log_to_file() function

Paramiko defines a function within `util.py` to handle all required steps to
log directly to a log file using the standard library `logging` module:

```python
def log_to_file(filename, level=DEBUG):
    """send paramiko logs to a logfile,
    if they're not already going somewhere"""
    l = logging.getLogger("paramiko")
    if len(l.handlers) > 0:
        return
    l.setLevel(level)
    f = open(filename, "a")
    lh = logging.StreamHandler(f)
    frm = "%(levelname)-.3s [%(asctime)s.%(msecs)03d] thr=%(_threadid)-3d"
    frm += " %(name)s: %(message)s"
    lh.setFormatter(logging.Formatter(frm, "%Y%m%d-%H:%M:%S"))
    l.addHandler(lh)
```

The function is called like so:

```python
paramiko.util.log_to_file("demo_sftp.log")
```

This allows all of the custonmization options normally associated with
the logging module without the complexity of having to define
handlers, formatters and other details directly within the top-most
portion of the main script any any other module in the project.

I'm not sure yet whether this is an example of code that could be better
controlled via an external configuration file (e.g., expose
customizations to the dev/user), but I suspect that the function is
intended for internal use within the Paramiko project itself and would not
be greatly enhanced by exposing the knobs via a config file.


#### SSHClient() class

Here the log "channel" override is left blank:

```python
    def __init__(self):
        """
        Create a new SSHClient.
        """
        self._system_host_keys = HostKeys()
        self._host_keys = HostKeys()
        self._host_keys_filename = None
        self._log_channel = None
        self._policy = RejectPolicy()
        self._transport = None
        self._agent = None
```

Later within the `connect()` method there is this:

```python
        t = self._transport = Transport(
            sock, gss_kex=gss_kex, gss_deleg_creds=gss_deleg_creds
        )

        # Code irrelevant to the discussion here

        if self._log_channel is not None:
            t.set_log_channel(self._log_channel)
```

and then there is this method which handles setting the channel name:

```python
    def set_log_channel(self, name):
        """
        Set the channel for logging.  The default is ``"paramiko.transport"``
        but it can be set to anything you want.

        :param str name: new channel name for logging
        """
        self._log_channel = name
```

It helpfully notes that the default is `paramiko.transport` unless overridden.

As I undestand it, this is a child logger for `paramiko`, which is itself a
child logger under the `root` logger.


#### Transport() class

Apparently this is where the child logger name is specified:

```python
        # ...
    def __init__(
        self,
        # ...

        self.log_name = "paramiko.transport"
        self.logger = util.get_logger(self.log_name)
        self.packetizer.set_log(self.logger)

        # ...
```

and here is where the user is allowed to override it:

```python
    # ...
    def set_log_channel(self, name):
        """
        Set the channel for this transport's logging.  The default is
        ``"paramiko.transport"`` but it can be set to anything you want. (See
        the `.logging` module for more info.)  SSH Channels will log to a
        sub-channel of the one specified.

        :param str name: new channel name for logging

        .. versionadded:: 1.1
        """
        self.log_name = name
        self.logger = util.get_logger(name)
        self.packetizer.set_log(self.logger)
```

and here is where a helper method is defined to retrieve the current child
logger name:

```python
    def get_log_channel(self):
        """
        Return the channel name used for this transport's logging.

        :return: channel name as a `str`

        .. versionadded:: 1.2
        """
        return self.log_name
    # ...
```


## References


- https://docs.python.org/3.4/library/logging.html
- https://github.com/paramiko/paramiko

- https://github.com/deoren/notes

