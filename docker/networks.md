# Docker: Networks

## Common commands

- Show networks
  - `docker network ls`

- Inspect default bridge network (different name, different driver)
  - Linux: `docker network inspect bridge`
  - Windows: `docker network inspect nat`

- Create new network
  - `docker network create -d bridge toll-bridge`
    - note: `-d bridge` is the default, so specifying it here as shown is not
      strictly required
    - attach new container by specifying the `--network NAME` flag
      - e.g., `docker container run --rm -d --network toll-bridge alpine`

## Bridge or "Single Host Networking"

- known as `docker0`
- oldest
- most common
- turned on by default
- any new containers will be added to this network unless you specify
  otherwise
- "crappiest" per [Nigel Poulton](#references)
- NAT
- Requires port-mappings
  - map port on the host to a port on a (specific) container
  - e.g., `docker container run --rm -d --name web -p 8080:80 nginx`
    - port 8080 on *all interfaces* on the host to port 80 in this container

## Overlay or "Multi-host Networking"

- single layer 2 network
- span multiple hosts
- IPs of specific backend Docker nodes are irrelevant
  - example in video shows nodes with IPs of 192.168.0.5, 172.32.46.43 and
    172.31.0.8
- created by `docker network create`
- attach containers to newly created network
- encrypted by way of `docker network create -o encrypted`
- built-in Overlay Network is container-only

## MACVLAN

- used to have containers talk to systems on existing VLANs
- known as **transparent driver** on Windows
- each container has its own IP Address and MAC Address on the existing network
- containers can be visible on network as any other device
- no bridges
- no port mapping
- requires `promiscuous mode` on the host NIC
  - cloud providers generally do not allow `promiscuous mode`
- potential to exceed the number of allowed MAC Addresses, causing performance
  to drop considerably

## IP

## References

- [Docker Deep Dive by Nigel Poulton, Pluralsight.com](https://www.pluralsight.com/courses/docker-deep-dive-update)
- <https://hicu.be/macvlan-vs-ipvlan>
- <http://hicu.be/bridge-vs-macvlan>
