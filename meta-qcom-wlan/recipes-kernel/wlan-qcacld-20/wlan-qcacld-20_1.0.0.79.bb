DESCRIPTION = "qcacld-2.0-1.0.0.79 module.bbclass mechanism."
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"
CAF_MIRROR = "https://www.codeaurora.org/cgit/external/wlan"
CFG_NAME = "cfg80211_030507"

inherit module

PR = "r1"
PV = "1.0.0.79"

SRC_URI = "file://Makefile \
           file://configure \
           file://drivers/Makefile \
           file://drivers/patches/patch-qcacld-20-10079 \
           file://COPYING \
          "

SRC_URI += "${CAF_MIRROR}/qcacld-2.0/snapshot/qcacld-2.0-${PV}.tar.gz"

S = "${WORKDIR}"

SRC_URI[md5sum] = "01106f5ab2f4751611de4bdc10c46dc9"
SRC_URI[sha256sum] = "a3b8ae06190e68712cd6d93a7df98eb688b1b1e758937db28517c7c79d01cfed"

do_configure() {
./configure
}

