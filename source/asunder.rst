*******
Asunder
*******

From the Asunder homepage:

   Asunder is a graphical Audio CD ripper and encoder for Linux. You can use it
   to save tracks from an Audio CD as any of WAV, MP3, OGG, FLAC, Opus, WavPack,
   Musepack, AAC, and Monkey's Audio files.

Build steps for Ubuntu
======================

*These steps were performed on an Ubuntu 17.10 VM. YMMV on other releases.*

#. ``cd /tmp``
#. ``wget http://littlesvr.ca/asunder/releases/asunder-2.9.3.tar.bz2``
#. ``tar xjf asunder-2.9.3.tar.bz2``
#. ``cd asunder-2.9.3``
#. ``sudo apt-get update``
#. ``sudo apt-get install build-essential intltool pkg-config
   libcddb2-dev libgtk2.0-dev``
#. ``./configure``
#. ``make``
#. ``sudo make install``

References
==========

- http://littlesvr.ca/asunder/downloads.php
- http://littlesvr.ca/asunder/releases/ChangeLog
