# Python: Logging

## Observations

### Paramiko module

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

## References


- https://docs.python.org/3.4/library/logging.html
- https://github.com/paramiko/paramiko

- https://github.com/deoren/notes

