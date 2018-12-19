package uk.co.alt236.tabclirun.resources;

import java.io.InputStream;
import java.util.Scanner;

public class Resources {
    public String readResourceAsString(final String name) {
        final InputStream is = Resources.class.getClassLoader().getResourceAsStream(name);
        if (is == null) {
            throw new IllegalStateException("Unable to find resource: '" + name + "'");
        }
        return new Scanner(is, "UTF-8").useDelimiter("\\A").next();
    }
}
