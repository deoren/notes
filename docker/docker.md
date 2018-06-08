# Docker notes


## Setting up Ubuntu repo

The steps notes below will walk you through enabling the official upstream repo and installing the needed packages to build and run Docker containers.

1. `sudo apt-get update
1. `sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common`
1. `curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -`
1. `sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"`
1. `sudo apt-get update`
1. `sudo apt-get install -y docker-ce`
1. `sudo docker run hello-world`

## Building Docker image

### Overview

1. `cd /path/to/working/directory`
1. `sudo docker build -t IMAGE_NAME:0.1 $PWD/`

### Example

1. `cd /tmp`
1. `git clone https://github.com/deoren/docker-testing`
1. `cd docker-testing/progit2/ubuntu-16.04`
1. `sudo docker build -t progit2-build:0.1 $PWD/`

## Running Docker image

1. `mkdir -p output`
1. `sudo docker run -ti -v $PWD/output:/output progit2-build:0.1`
