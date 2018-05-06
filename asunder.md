# Asunder

From the Asunder homepage:

> Asunder is a graphical Audio CD ripper and encoder for Linux. You can use it
> to save tracks from an Audio CD as any of WAV, MP3, OGG, FLAC, Opus, WavPack,
> Musepack, AAC, and Monkey's Audio files.

## Build steps for Ubuntu

*These steps were performed on an Ubuntu 17.10 VM. YMMV on other releases.*

1. `cd /tmp`
1. `wget http://littlesvr.ca/asunder/releases/asunder-2.9.3.tar.bz2`
1. `tar xjf asunder-2.9.3.tar.bz2`
1. `cd asunder-2.9.3`
1. `sudo apt-get update`
1. `sudo apt-get install build-essential intltool pkg-config libcddb2-dev libgtk2.0-dev`
1. `./configure`
1. `make`
1. `sudo make install`

## References

- http://littlesvr.ca/asunder/downloads.php
- http://littlesvr.ca/asunder/releases/ChangeLog
