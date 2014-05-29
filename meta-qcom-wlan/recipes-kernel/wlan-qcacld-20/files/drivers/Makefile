#
export ATH_DRIVER_TOPDIR=${ATH_TOPDIR}/drivers

#################################################################################################################################

KBUILD_OPTIONS := WLAN_ROOT=$(PWD)/$(FW_WLAN_NAME)
KBUILD_OPTIONS += MODNAME=wlan
LICENSE_FILE ?= $(PWD)/$(FW_WLAN_NAME)/CORE/HDD/src/wlan_hdd_main.c
WLAN_OPEN_SOURCE = $(shell if grep -q "MODULE_LICENSE(\"Dual BSD/GPL\")" \
		$(LICENSE_FILE); then echo 1; else echo 0; fi)
WLAN_OPEN_SOURCE = 0
WLAN_SELECT := CONFIG_QCA_CLD_WLAN=m
KBUILD_OPTIONS += CONFIG_QCA_WIFI_ISOC=0
KBUILD_OPTIONS += CONFIG_QCA_WIFI_2_0=1
KBUILD_OPTIONS += $(WLAN_SELECT)
KBUILD_OPTIONS += WLAN_OPEN_SOURCE=$(WLAN_OPEN_SOURCE)
KBUILD_OPTIONS += $(KBUILD_EXTRA) # Extra config if any

export WLAN_DRIVER_TOPDIR=${ATH_TOPDIR}/drivers
export FW_WLAN_NAME="qcacld-2.0-1.0.0.105"

#################################################################################################################################
all: driver_clean driver_build

clean: driver_clean

driver_build:
	@echo Build drivers
	${MAKEARCH} -C $(KERNELPATH) SUBDIRS=$(ATH_DRIVER_TOPDIR)/$(FW_WLAN_NAME) modules $(KBUILD_OPTIONS)

driver_clean:
	@echo Clean drivers
	${MAKEARCH} -C $(KERNELPATH) SUBDIRS=$(ATH_DRIVER_TOPDIR)/$(FW_WLAN_NAME) clean

