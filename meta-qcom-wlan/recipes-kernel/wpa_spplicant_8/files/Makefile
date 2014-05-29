#
# Main Makefile
#
export ATH_TOPDIR=$(shell pwd)
#export KERNELPATH=${ATH_TOPDIR}/../../linux-yocto/3.5-r0/linux-genericx86-standard-build
#export KERNELARCH=i386
#export TOOLPREFIX=
export KERNELPATH=${ATH_TOPDIR}/../../linux-imx/3.5.7-1.0.0-r32.5/git
export KERNELARCH=arm
export TOOLPREFIX=arm-poky-linux-gnueabi-

export MAKEARCH=make ARCH=$(KERNELARCH) CROSS_COMPILE=$(TOOLPREFIX)
export INSTALL_ROOT=${ATH_TOPDIR}/rootfs-$(KERNELARCH).build
export ROOTFS_TOPDIR=${ATH_TOPDIR}/rootfs
export WLAN_DRIVER_TOPDIR=${ATH_TOPDIR}/drivers

export WPA_SUPPLICANT_TOPDIR=${ATH_TOPDIR}/apps/wpa_supplicant_8
export HOSTAPD_TOPDIR=${ATH_TOPDIR}/apps/wpa_supplicant
# Use local libnl library or not
export BUILD_LIBNL=y
export INCLUDE_LIBNL11_HEADER=${ATH_TOPDIR}/apps/libnl-1/include
export INCLUDE_LIBNL11_LIB=-lm ${ATH_TOPDIR}/apps/libnl-1/lib/libnl.a -lm
export LIBNL11_TOPDIR=${ATH_TOPDIR}/apps/libnl-1

# Use local openssl library or not
export BUILD_OPENSSL=y
export INCLUDE_OPENSSL_HEADER=${ATH_TOPDIR}/apps/openssl-1.0.1f/include
export INCLUDE_OPENSSL_LIB=${ATH_TOPDIR}/apps/openssl-1.0.1f/
export LIBOPENSSL_TOPDIR=${ATH_TOPDIR}/apps/openssl-1.0.1f

export FW_WLAN_NAME="qcacld-2.0-1.0.0.105"
export CONFIG_ROME_IF=pci
export ATH_DRIVER_TOPDIR=${ATH_TOPDIR}/drivers

targetList := wpa_supplicant hostapd_build
default: ${targetList}

clean: drivers_clean rootfs_clean wpa_supplicant_clean hostapd_clean

#
# rootfs build
#
rootfs_prep:
	@mkdir -p $(INSTALL_ROOT)
	@mkdir -p $(INSTALL_ROOT)/sbin
	@mkdir -p $(INSTALL_ROOT)/usr/sbin
	@mkdir -p $(INSTALL_ROOT)/lib/modules
	@mkdir -p $(INSTALL_ROOT)/etc

rootfs_build:
	@echo Build rootfs
	@cp -Rf $(ROOTFS_TOPDIR)/* $(INSTALL_ROOT)

rootfs_clean:
	@echo Clean rootfs
	@rm -rf $(INSTALL_ROOT)

#
# drivers build
#
drivers: rootfs_prep
	@echo Build drivers
	cd ${WLAN_DRIVER_TOPDIR} && ${MAKEARCH}
	find ${WLAN_DRIVER_TOPDIR} -name '*.ko' -exec cp '{}' $(INSTALL_ROOT)/lib/modules ';'
	
drivers_clean:
	@echo Clean drivers
	cd ${WLAN_DRIVER_TOPDIR} && ${MAKEARCH} clean
	
modules_install:
	find ${WLAN_DRIVER_TOPDIR} -name '*.ko' -exec cp '{}' $(INSTALL_ROOT)/lib/modules ';'

#
# wpa_supplicant build
#
wpa_supplicant: rootfs_prep wpa_supplicant_clean
	@echo Build wpa_supplicant
	cd $(WPA_SUPPLICANT_TOPDIR)/wpa_supplicant && \
	mkdir -p $(INSTALL_ROOT)/sbin/systemd $(INSTALL_ROOT)/sbin/dbus && \
	${MAKEARCH} CC=$(TOOLPREFIX)gcc AR=$(TOOLPREFIX)ar LD=$(TOOLPREFIX)ld && \
	${MAKEARCH} DESTDIR=$(INSTALL_ROOT) BINDIR=/sbin LIBDIR=/lib install

wpa_supplicant_clean:
	@echo Clean wpa_supplicant
	cd $(WPA_SUPPLICANT_TOPDIR)/wpa_supplicant && ${MAKEARCH} clean

#
# hostapd build
#
hostapd_build: rootfs_prep hostapd_clean
	@echo Build hostapd tool
	cd $(HOSTAPD_TOPDIR)/hostapd && \
	${MAKEARCH} && \
	cp $(HOSTAPD_TOPDIR)/hostapd/hostapd $(INSTALL_ROOT)/sbin

hostapd_clean:
	@echo Clean hostapd tool
	cd $(HOSTAPD_TOPDIR)/hostapd && \
	${MAKEARCH} clean
