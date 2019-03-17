# Docker: Networks

## Common commands

- Show networks
  - `docker network ls`

- Inspect default bridge network (different name, different driver)
  - Linux: `docker network inspect bridge`
  - Windows: `docker network inspect nat`

## Bridge or "Single Host Networking"

- known as `docker0`
- oldest
- most common
- turned on by default
- any new containers will be added to this network unless you specify
  otherwise
- "crappiest" per [Nigel Poulton](#references)
- NAT
- scoped to `local`
- Requires port-mappings
  - map port on the host to a port on a (specific) container
  - e.g., `docker container run --rm -d --name web -p 8080:80 nginx`
    - port 8080 on *all interfaces* on the host to port 80 in this container

- Create new `bridge` network
  - `docker network create -d bridge NEW_BRIDGE_NAME`
    - e.g., - `docker network create -d bridge toll-bridge`
      - note: `-d bridge` is the default, so specifying it here as shown is not
        strictly required
    - attach new container by specifying the `--network NAME` flag
      - e.g., `docker container run --rm -d --network toll-bridge alpine`

## Overlay or "Multi-host Networking"

- requires Swarm mode to function
- single layer 2 network
- span multiple hosts
- IPs of specific backend Docker nodes are irrelevant
  - example in video shows nodes with IPs of 192.168.0.5, 172.32.46.43 and
    172.31.0.8
- created by `docker network create`
- attach containers to newly created network
- encrypted by way of `docker network create -o encrypted`
- built-in Overlay Network is container-only
- scoped to `swarm`

- Create new `overlay` network
  - `docker network create -d overlay NEW_BRIDGE_NAME`
    - e.g., - `docker network create -d overlay skynet`
      - note: `-d bridge` is the default, so specifying it here as shown is not
        strictly required
    - attach new container by specifying the `--network NAME` flag
      - e.g., `docker container run --rm -d --network skynet alpine`

- Create new service on `overlay` network
  - `docker service create -d --name example --replicas 2 --network skynet alpine`
    - Note: Because they're on the same overlay network, the two replicas
      (containers) can ping each other

- New overlay networks are not immediately created on other nodes until needed
  by a container running on one of those other swarm nodes
  1. Create overlay network on `node1` of a multi-host swarm
  1. Run `docker network ls` on `node1`, see the network
  1. Run `docker network ls` on `node2`, do NOT see the network

## MACVLAN

- used to have containers talk to systems on existing VLANs
- known as **transparent driver** on Windows
- each container has its own IP Address and MAC Address on the existing network
- containers can be visible on network as any other device
- no bridges
- no port mapping
- cannot connect directly to parent interface
  - Linux kernel filters out traffic between the container and the parent interface
- requires `promiscuous mode` on the host NIC
  - cloud providers generally do not allow `promiscuous mode`
- potential to exceed the number of allowed MAC Addresses, causing performance
  to drop considerably
- When you provide the address space for the VLAN it relates to, make sure to
  give a range of addresses that are not already in use on the VLAN or are not
  in a pool of addresses that a DHCP server will hand out. If you are not
  careful, using MACVLAN will result in duplicate IPs on the network.

## IPVLAN

- similar to Linux MACVLAN
- no bridges
- subinterfaces
- unique IP Address per container
- shares MAC Address with host
- requires special considerations when using DHCP
  - DHCP servers expect to deal with MAC Addresses
- cannot connect directly to parent interface
  - Linux kernel filters out traffic between the container and the parent interface

## References

- [Docker Deep Dive by Nigel Poulton, Pluralsight.com](https://www.pluralsight.com/courses/docker-deep-dive-update)
- <https://hicu.be/macvlan-vs-ipvlan>
- <http://hicu.be/bridge-vs-macvlan>
