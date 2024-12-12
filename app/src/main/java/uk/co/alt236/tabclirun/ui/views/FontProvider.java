package uk.co.alt236.tabclirun.ui.views;

import java.awt.*;

class FontProvider {

    public String getMonospaceFontName() {
        return getFont("Book", Font.MONOSPACED);
    }

    private String getFont(final String fontName,
                           final String fallback) {
        final GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();

        for (String font : g.getAvailableFontFamilyNames()) {
            //System.out.println(font);
            if (font.equals(fontName)) {
                return fontName;
            }
        }

        return fallback;
    }
}
