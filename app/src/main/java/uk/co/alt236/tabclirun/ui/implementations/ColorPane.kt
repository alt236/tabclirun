package uk.co.alt236.tabclirun.ui.implementations

import java.awt.Color
import javax.swing.JTextPane
import javax.swing.text.DefaultStyledDocument
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants
import javax.swing.text.StyleContext

class ColorPane : JTextPane() {

    init {
        CutCopyPasteMenu().attach(this)
    }

    fun append(color: Color?, text: String?) {
        val style = StyleContext.getDefaultStyleContext()
        val attr = style.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color)
        val len = document.length // same value as getText().length();

        caretPosition = len // place caret at the end (with no selection)
        setCharacterAttributes(attr, false)
        replaceSelection(text) // there is no selection, so inserts at caret
    }

    fun set(color: Color?, text: String?) {
        val doc = DefaultStyledDocument()
        val attr = SimpleAttributeSet()
        attr.addAttribute(StyleConstants.Foreground, color)

        doc.insertString(0, text, attr);
        document = doc
    }
}