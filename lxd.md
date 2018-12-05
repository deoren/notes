# LXD

## Install packages

### Ubuntu 16.04

1. `sudo apt-get install lxd lxd-client`
1. `sudo lxd init`

## Initial setup

### Network settings

In short, run `sudo lxd init` and answer the prompts.

When you run `sudo lxd init` you will be asked whether you wish to configure
the LXD bridge. Answer Yes and a random subnet will be chosen. In my case
10.245.28.1 was chosen as the IP with 255.255.255.0 as the mask. DHCP was
enabled for container use.

The network bridge device was named `lxdbr0`. NAT for IPv4 was enabled, IPv6
was disabled.

## Show Image Servers

These systems host images that can be used to generate new containers. You do
NOT have to run this command as root.

`lxc remote list`

## Show filtered image list

`lxc image list images: IMAGE_NAME_ALIAS`

For example, this is how you would show available CentOS images:

`lxc image list images: centos`

Output:

```shell
+------------------------+--------------+--------+---------------------------------+--------+---------+------------------------------+
|         ALIAS          | FINGERPRINT  | PUBLIC |           DESCRIPTION           |  ARCH  |  SIZE   |         UPLOAD DATE          |
+------------------------+--------------+--------+---------------------------------+--------+---------+------------------------------+
| centos/6 (3 more)      | d724d5f1ebad | yes    | Centos 6 amd64 (20181202_02:16) | x86_64 | 75.59MB | Dec 2, 2018 at 12:00am (UTC) |
+------------------------+--------------+--------+---------------------------------+--------+---------+------------------------------+
| centos/6/i386 (1 more) | 3a7cebdff49a | yes    | Centos 6 i386 (20181202_02:16)  | i686   | 75.77MB | Dec 2, 2018 at 12:00am (UTC) |
+------------------------+--------------+--------+---------------------------------+--------+---------+------------------------------+
| centos/7 (3 more)      | ff54a5e8bd3c | yes    | Centos 7 amd64 (20181202_02:16) | x86_64 | 83.47MB | Dec 2, 2018 at 12:00am (UTC) |
+------------------------+--------------+--------+---------------------------------+--------+---------+------------------------------+
```

## Create container

`lxc init images:centos/7 centos-test`

## Start container

`lxc start centos-test`

## Create AND Start container

`lxc launch images:centos/7 centos-test`

## Stop container

`lxc stop centos-test`

## Delete container

**WARNING:**: You are NOT prompted to confirm you wish to delete the container.

`lxc delete centos-test`

To play it safe, you probably should one of these commands instead for interactive confirmation:

- `lxc delete --interactive centos-test`
- `lxc delete -i centos-test`

## References

- [How to create a LXD Container with your ssh key in it (and with ssh server in the container)](https://gist.github.com/jeanlouisferey/15be1f421eb9f9a66f1c74d410de2675)
