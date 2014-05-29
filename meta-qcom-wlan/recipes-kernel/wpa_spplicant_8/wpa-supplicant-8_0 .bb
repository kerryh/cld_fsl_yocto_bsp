DESCRIPTION = "apps.bbclass mechanism."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"

inherit module

PR = "r0"
PV = "0"

SRC_URI = "file://Makefile \
           file://configapps \
           file://COPYING \
          "

SRC_URI += "file://apps.tar.gz;name=tarball"

SRC_URI[tarball.md5sum] = "bdd96020017cd76ebbb2db0dff6f1c21"
SRC_URI[tarball.sha256sum] = "0243dd2333486bbbe774fd112b504277c145bcaddf97026903aac60cc3b2b3c9"

S = "${WORKDIR}"

do_configure() {
./configapps
}

