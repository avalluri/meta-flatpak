DESCRIPTION = "Helper utilities for flatpak-based applications/services."
HOMEPAGE = "http://github.com/klihub/flatpak-utils"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENSE-BSD;md5=f9f435c1bd3a753365e799edf375fc42"

DEPENDS = "flatpak systemd"

SRC_URI = " \
    git://git@github.com/klihub/flatpak-utils.git;protocol=https;branch=master \
  "

SRCREV = "14d2b39ad2d00db50ae9d95e65aa1380524d09ff"

inherit autotools pkgconfig systemd

AUTO_LIBNAME_PKGS = ""

S = "${WORKDIR}/git"

EXTRA_OECONF += "--with-systemdunitdir=${systemd_unitdir}"

# possible package configurations
PACKAGECONFIG ??= ""

FILES_${PN} = "\
    ${systemd_unitdir}/system-generators/flatpak-session-enable \
    ${bindir}/flatpak-session \
    ${libexecdir}/flatpak-utils \
    ${systemd_unitdir}/system/flatpak-sessions.target \
    ${systemd_unitdir}/system/flatpak-session@.service \
    ${systemd_unitdir}/system/flatpak-update.service \
"

FILES_${PN}-dbg =+ "${base_libdir}/systemd/system-generators/.debug"

SYSTEMD_PACKAGES      += "${PN}"
SYSTEMD_SERVICE_${PN}  = "flatpak-sessions.target flatpak-update.service"
SYSTEMD_AUTO_ENABLE    = "enable"

