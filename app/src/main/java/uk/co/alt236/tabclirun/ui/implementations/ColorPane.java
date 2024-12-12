package uk.co.alt236.tabclirun.ui.implementations;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;

public class ColorPane extends JTextPane {

    public void append(Color color, String text) {
        final StyleContext sc = StyleContext.getDefaultStyleContext();
        final AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
        final int len = getDocument().getLength(); // same value as getText().length();

        setCaretPosition(len); // place caret at the end (with no selection)
        setCharacterAttributes(aset, false);
        replaceSelection(text); // there is no selection, so inserts at caret
    }
}