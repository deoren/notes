# LXD: `lxd-p2c`

## `lxd-p2c`: You must explicitly list all mount points

When running `lxd-p2c`, you can optionally specify multiple paths (i.e., mount
points) that should be copied from the source system to the specified (new)
container. If you don't specify those paths, content from those mounted
volumes will _not_ be copied over. For example, if you run this command:

`$ sudo ./lxd-p2c https://lxd1.example.com wordpress-test /`

then you will not have the content from the `/boot` mount point (assuming it
*is* a separate mount point) in the new container.

If you want the content from `/boot`, then you will need to run this command:

`$ sudo ./lxd-p2c https://lxd1.example.com wordpress-test / /boot`

This will copy the content from the `/boot` volume into the new (flat)
container filesystem.

## `lxd-p2c`: Comment out unreachable `/etc/fstab` entries

*you may need to comment out **all** entries*

If you fail to do so, Ubuntu will fail to enable networking at boot time and
you will spend hours and hours trying to figure out why the networking stack
is broken. I specifically encountered this with Ubuntu 14.04, but I think the
behavior persists even into an Ubuntu 14.04 VM that has been upgraded to
16.04.

As a temporary workaround, you can run `dhclient eth0` (as root) from a
container with an inactive network configuration.
