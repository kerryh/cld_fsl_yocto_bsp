DESCRIPTION = "libnl-1-1.bbclass mechanism."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"

inherit module

PR = "r0"
PV = "0"

SRC_URI = "file://Makefile \
           file://configlibnl \
           file://COPYING \
          "

SRC_URI += "file://libnl-1.1.4.tar.gz;name=tarball"

SRC_URI[tarball.md5sum] = "580cb878be536804daca87fb75ae46cc"
SRC_URI[tarball.sha256sum] = "4f80c21fe5bbcdde6e72b59b4f98306063a41421f909887c34e58d93e746d063"

S = "${WORKDIR}"

do_configure() {
./configlibnl
}

