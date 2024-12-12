package uk.co.alt236.tabclirun.ui.views.tabtitle

import java.awt.Image.SCALE_SMOOTH
import javax.swing.Icon
import javax.swing.ImageIcon


internal class TabTitleIcons {

    val iconError: Icon by lazy {
        ImageIcon(this.javaClass.getResource("/tab-icons/run-fail.png")).scale()
    }

    val iconSuccess: Icon by lazy {
        ImageIcon(this.javaClass.getResource("/tab-icons/run-ok.png")).scale()
    }

    val iconInProgress: Icon by lazy {
        ImageIcon(this.javaClass.getResource("/tab-icons/run-in-progress.png")).scale()
    }


    private fun ImageIcon.scale() = ImageIcon(this.image.getScaledInstance(ICON_SIZE, ICON_SIZE, SCALE_SMOOTH))

    private companion object {
        const val ICON_SIZE = 25
    }
}