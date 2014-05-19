#
# Main Makefile
#
export ATH_TOPDIR=$(shell pwd)
export BOARD_TYPE=imx6
export KERNELPATH=${ATH_TOPDIR}/../../linux-imx/3.5.7-1.0.0-r32.5/git
export KERNELARCH=arm
export TOOLPREFIX=arm-poky-linux-gnueabi-

export MAKEARCH=make ARCH=$(KERNELARCH) CROSS_COMPILE=$(TOOLPREFIX)
export INSTALL_ROOT=${ATH_TOPDIR}/rootfs-$(BOARD_TYPE).build
export ROOTFS_TOPDIR=${ATH_TOPDIR}/rootfs
export WLAN_DRIVER_TOPDIR=${ATH_TOPDIR}/drivers
export FW_WLAN_NAME="qcacld-2.0-1.0.0.79"
export CONFIG_ROME_IF=pci
export ATH_DRIVER_TOPDIR=${ATH_TOPDIR}/drivers

targetList := drivers
default: ${targetList}

clean: drivers_clean rootfs_clean

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
