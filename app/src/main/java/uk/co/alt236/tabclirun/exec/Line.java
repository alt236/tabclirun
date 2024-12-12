package uk.co.alt236.tabclirun.exec;

public class Line {
    private final String line;
    private boolean error;

    public Line(String line, boolean error) {
        this.line = line;
        this.error = error;
    }

    public String getText() {
        return line;
    }

    public boolean isError() {
        return error;
    }
}
