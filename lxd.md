# LXD

## Install packages

### Ubuntu 16.04

1. `sudo apt-get install lxd lxd-client`
1. `sudo lxd init`

## Network settings

When you run `sudo lxd init` you will be asked whether you wish to configure
the LXD bridge. Answer Yes and a random subnet will be chosen. In my case
10.245.28.1 was chosen as the IP with 255.255.255.0 as the mask. DHCP was
enabled for container use.

The network bridge device was named `lxdbr0`. NAT for IPv4 was enabled, IPv6
was disabled.

## Create container

## Initial setup

## References
