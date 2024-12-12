package uk.co.alt236.tabclirun.ui.views.common

import java.awt.Font
import java.awt.GraphicsEnvironment

internal class FontProvider {
    val monospaceFontName: String
        get() = getFont("Book", Font.MONOSPACED)

    private fun getFont(fontName: String, fallback: String): String {
        val g = GraphicsEnvironment.getLocalGraphicsEnvironment()

        for (font in g.availableFontFamilyNames) {
            //System.out.println(font);
            if (font == fontName) {
                return fontName
            }
        }

        return fallback
    }
}
