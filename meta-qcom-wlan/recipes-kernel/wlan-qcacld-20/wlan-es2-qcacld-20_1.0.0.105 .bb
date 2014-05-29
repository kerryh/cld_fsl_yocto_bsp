DESCRIPTION = "qcacld-2.0-1.0.0.105 module.bbclass mechanism."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"
CAF_MIRROR = "https://www.codeaurora.org/cgit/external/wlan"
CFG_NAME = "cfg80211_030507"

inherit module

PR = "r0"
PV = "1.0.0.105"

SRC_URI = "file://Makefile \
           file://configure \
           file://drivers/Makefile \
           file://drivers/patches/patch-qcacld-20-100105 \
           file://COPYING \
          "

SRC_URI += "${CAF_MIRROR}/qcacld-2.0/snapshot/qcacld-2.0-${PV}.tar.gz"

S = "${WORKDIR}"

SRC_URI[md5sum] = "9cff2a4945fe55a38bbafe944ec318d3"
SRC_URI[sha256sum] = "9b28ee9fc07f3a6ed68b177325cdce9a3efb777ddba573f48f7de80962214f62"

do_configure() {
./configure
}

