# Fresh Ubuntu installation

*The following steps would be used to spin up a new dev node for local testing,
either directly within the new installation or against a series of containers.*

## Initial steps

1. Install Ubuntu
    - e.g., via ISO, LTS from Windows Store, etc
1. Install Subversion
1. Export `/etc/apt/sources.list` file
1. Export Squid proxy server settings file
1. Add Git PPA
    - ppa:git-core/ppa
1. Add Ansible PPA
    - ppa:ansible/ansible
1. Add LXC PPA (16.04 needed, 18.04 NOT needed)
    - ppa:ubuntu-lxc/lxc-stable
1. Add Golang PPA
    - ppa:longsleep/golang-backports
1. `sudo apt-get update`
1. `sudo apt-get dist-upgrade`
1. `sudo reboot`
1. Install common tools
    - tmux
    - git
    - ansible
    - lxc (likely does not work on WSL)
    - docker (likely also does not work on WSL)

Future steps would use a shell script for bootstrapping and installing Ansible
so that one or more playbooks could be used for the rest of the work.

## References

- <https://launchpad.net/~git-core/+archive/ubuntu/ppa>
- <https://launchpad.net/~ansible/+archive/ubuntu/ansible>
- <https://launchpad.net/~ubuntu-lxc/+archive/ubuntu/lxc-stable>
- <https://github.com/golang/go/wiki/Ubuntu>
