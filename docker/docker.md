# Docker notes

## Installation

### Setting up Ubuntu repo

The steps notes below will walk you through enabling the official upstream repo and installing the needed packages to build and run Docker containers.

1. `sudo apt-get update
1. `sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common`
1. `curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -`
1. `sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"`
1. `sudo apt-get update`
1. `sudo apt-get install -y docker-ce`
1. `sudo docker run hello-world`

## Configuration

### Allowing non-root user (sans sudo) `docker` command execution

The guide clearly notes that this change is for the user's convenience
and should only be done for users who otherwise already have unlimited `sudo`
access.

Note: The upstream Ubuntu repo packages already create the `docker` group.

1. `sudo groupadd docker`
1. `sudo usermod --append --groups docker USERNAME_HERE`
1. `newgrp docker`
1. `docker run hellow-world`

## Building Docker image

### Overview

1. `cd /path/to/working/directory`
1. `sudo docker build -t IMAGE_NAME:0.1 $PWD/`

### Example

1. `cd /tmp`
1. `git clone https://github.com/deoren/docker-testing`
1. `cd docker-testing/progit2/ubuntu-16.04`
1. `sudo docker build -t progit2-build:0.1 $PWD/`

### Specifying multiple tags

#### At build time

- `docker build -t name1:tag1 -t name1:tag2 -t name2 .`
- `docker build --no-cache -t squid:3.5.27-1ubuntu1.1 -t squid:3.5.27 -t squid:latest .`

### After building an image

*Context: The original image was built with just the `squid:latest` tag and
was given the hash/id of `62f030936d95`*

1. `docker images ls`
1. `$ docker tag 62f030936d95 squid:3.5.27-1ubuntu1.1`
1. `$ docker tag 62f030936d95 squid:3.5.27`
1. `$ docker tag 62f030936d95 squid:2019-01-11`

## Running Docker image

### Interactive

1. `mkdir -p output`
1. `sudo docker run -ti -v $PWD/output:/output progit2-build:0.1`

### Background / daemon / one-shot

1. `mkdir -p output`
1. `sudo docker run -d -v $PWD/output:/output progit2-build:0.1`

## References

- <https://askubuntu.com/questions/477551/how-can-i-use-docker-without-sudo/477554#477554>
- <https://stackoverflow.com/questions/21928780/create-multiple-tag-docker-image>
