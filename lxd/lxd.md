# LXD

- [LXD](#lxd)
  - [Install packages](#install-packages)
    - [Ubuntu 16.04](#ubuntu-1604)
  - [Initial setup](#initial-setup)
    - [Network settings](#network-settings)
      - [LXD 2.0](#lxd-20)
      - [LXD 2.5+](#lxd-25)
  - [Show Image Servers](#show-image-servers)
  - [Show filtered image list](#show-filtered-image-list)
  - [Create container](#create-container)
  - [Start container](#start-container)
  - [Create AND Start container](#create-and-start-container)
  - [Login to container](#login-to-container)
    - [as `ubuntu` user account](#as-ubuntu-user-account)
    - [as root account](#as-root-account)
  - [Stop container](#stop-container)
  - [Create image from container](#create-image-from-container)
  - [Delete container](#delete-container)
  - [References](#references)

## Install packages

### Ubuntu 16.04

1. `sudo apt-get install lxd lxd-client`
1. `sudo lxd init`

## Initial setup

### Network settings

In short, run `sudo lxd init` and answer *most* of the prompts. See the
sections below for specifics.

#### LXD 2.0

When you run `sudo lxd init` you will be asked whether you wish to configure
the LXD bridge.

Answer **Yes** and a random subnet will be chosen. In my case 10.245.28.1 was
chosen as the IP with 255.255.255.0 as the mask. DHCP was enabled for
container use.

The network bridge device was named `lxdbr0`. NAT for IPv4 was enabled, IPv6
was disabled.

#### LXD 2.5+

*Most of the information here was pulled from the **LXD, ZFS and bridged
networking on Ubuntu 16.04 LTS+** link in the Reference section*

When you run `sudo lxd init` you will be asked whether you wish to configure
the LXD bridge.

Answer **No**, see the notes below.

In version 2.5 and newer, the new `lxc network` command is used to configure
networking.

With `lxd init` complete, add the `br0` interface to the default profile
with:

`lxc network attach-profile br0 default eth0`

If you receive this error:

```ShellSession
device already exists.
```

then your system has the `lxdbr0` interface attached to the default profile.
This interface must first be removed from the default profile before the `br0`
interface can be attached.

Run the following command to detach the `lxdbr0` interface from the default
profile:

`lxc network detach-profile lxdbr0 default eth0`

With that complete, LXD will now successfully use the pre-configured bridge.

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

## Login to container

### as `ubuntu` user account

`lxc exec <container-name> -- sudo --login --user <username>`

### as root account

- `lxc exec <container-name> -- /bin/bash`
- `lxc exec <container-name> -- sudo /bin/bash`

## Stop container

`lxc stop centos-test`

## Create image from container

1. Stop the container
    - `lxc stop <container-name>`
1. Publish the container
    - Public image
        - `lxc publish --public <container-name> --alias=custom-build-tools-v1`
    - Private image
      - `lxc publish <container-name> --alias=custom-build-tools-v1`
1. Optional: Push image over to shared LXD server (as backup or for sharing)
   - `lxc image copy <container-alias> <remote-name>:`

Notes:

- The `--public` flag allows the image to be retrieved by any client that can
  reach the LXD server where the image was first published or at
  `<remote-name>:`
- Omitting `--public` causes the container to be published as a private image.

## Delete container

**WARNING:** You are NOT prompted to confirm you wish to delete the container.

`lxc delete centos-test`

To play it safe, you probably should one of these commands instead for interactive confirmation:

- `lxc delete --interactive centos-test`
- `lxc delete -i centos-test`

## References

- [How to create a LXD Container with your ssh key in it (and with ssh server in the container)](https://gist.github.com/jeanlouisferey/15be1f421eb9f9a66f1c74d410de2675)

- [LXD, ZFS and bridged networking on Ubuntu 16.04 LTS+](https://bayton.org/docs/linux/lxd/lxd-zfs-and-bridged-networking-on-ubuntu-16-04-lts/)

- <https://askubuntu.com/questions/381099/how-to-log-into-lxc-container>
- <https://askubuntu.com/questions/1052643/lxd-how-to-execute-a-script-inside-a-container-from-host>
- <https://blog.ubuntu.com/2015/06/30/publishing-lxd-images>
