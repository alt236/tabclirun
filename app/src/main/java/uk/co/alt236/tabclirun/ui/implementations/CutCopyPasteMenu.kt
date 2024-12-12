package uk.co.alt236.tabclirun.ui.implementations

import java.awt.event.InputEvent
import java.awt.event.KeyEvent
import javax.swing.AbstractAction
import javax.swing.JMenu
import javax.swing.JMenuItem
import javax.swing.JPopupMenu
import javax.swing.KeyStroke
import javax.swing.text.DefaultEditorKit.*
import javax.swing.text.JTextComponent
import javax.swing.text.TextAction

class CutCopyPasteMenu {
    private val menu: JMenu = JMenu("Edit")
    private var popupMenu: JPopupMenu = JPopupMenu()

    init {
        addAction(CutAction(), KeyEvent.VK_X, "Cut")
        addAction(CopyAction(), KeyEvent.VK_C, "Copy")
        addAction(PasteAction(), KeyEvent.VK_V, "Paste")
    }

    private fun addAction(action: TextAction, key: Int, text: String) {
        action.putValue(
            AbstractAction.ACCELERATOR_KEY,
            KeyStroke.getKeyStroke(key, InputEvent.CTRL_DOWN_MASK)
        )
        action.putValue(AbstractAction.NAME, text)
        menu.add(JMenuItem(action))
        popupMenu.add(JMenuItem(action))
    }

    fun attach(vararg components: JTextComponent) {
        for (tc in components) {
            tc.componentPopupMenu = popupMenu
        }
    }
}