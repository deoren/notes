# Installing Golang

## Overview

*As of this writing, none of the steps provided in this doc have been tested.
YMMV.*

This doc serves as a quick reminder for how to install the latest version of
the Golang build packages.

## Windows

1. Download new MSI
1. Uninstall old version if present
1. Install new MSI
1. Upgrade Visual Studio Code extension (if applicable)

## Linux

### General

1. Download latest tarball from <https://golang.org/doc/install> to `/tmp`
1. `sudo tar -C /usr/local -xzf go1.11.5.linux-amd64.tar.gz`
1. Update `$HOME/.profile` with settings shown below
1. Logout
1. Login

Settings:

```shell
# Where Go projects live
export GOPATH="$HOME/go"

# Path of downloaded/extracted tarball from upstream
export GOROOT="/usr/local/go"

# https://golang.org/doc/install
export PATH=$PATH:$GOROOT/bin:$GOPATH/bin
```

### Ubuntu

#### PPA

1. `sudo apt-repository-add ppa:longsleep/golang-backports`
1. `sudo apt-get update`
1. `sudo apt-get install golang-go`

If you later decide that you wish to use the binaries directly from Google
and extract the tarball yourself, you can remove the packages and the PPA via
these commands:

1. `sudo apt-get remove --purge $(dpkg -l | grep longsleep | awk '{print $2}')`
1. `sudo apt-add-repository --remove ppa:longsleep/golang-backports`

You should be prompted for confirmation when running both commands.

#### Custom Deb package

1. `mkdir -p ~/.go/bin`
1. `echo "GOPATH DEFAULT=\${HOME}/.go" >> ~/.pam_environment`
1. `echo "PATH DEFAULT=\${PATH}:\$GOPATH/bin" >> ~/.pam_environment`
1. Logout, then login (so environment settings are applied)
1. `wget -O /tmp/godeb-amd64.tar.gz https://godeb.s3.amazonaws.com/godeb-amd64.tar.gz`
1. `tar xfz /tmp/godeb-amd64.tar.gz -C ~/.go/bin`
1. `godeb install`

## References

- <https://blog.labix.org/2013/06/15/in-flight-deb-packages-of-go>
- <https://github.com/niemeyer/godeb>
- <https://askubuntu.com/questions/513/any-ppas-for-googles-go-language>
- <https://github.com/golang/go/wiki/Ubuntu>
- <https://golang.org/dl/>
