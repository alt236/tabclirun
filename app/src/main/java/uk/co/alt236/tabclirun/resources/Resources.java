package uk.co.alt236.tabclirun.resources;

import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Resources {
    public String readResourceAsString(final String name) {
        return new Scanner(getResourceStream(name), "UTF-8").useDelimiter("\\A").next();
    }

    public Image getImage(final String name) {
        return Toolkit.getDefaultToolkit().getImage(getResourceUrl(name));
    }

    private InputStream getResourceStream(final String name) {
        final InputStream is = Resources.class.getClassLoader().getResourceAsStream(name);
        if (is == null) {
            throw new IllegalArgumentException("Unable to find resource: '" + name + "'");
        }
        return is;
    }

    private URL getResourceUrl(final String name) {
        final URL url = Resources.class.getClassLoader().getResource(name);
        if (url == null) {
            throw new IllegalArgumentException("Unable to find resource: '" + name + "'");
        }
        return url;
    }
}
