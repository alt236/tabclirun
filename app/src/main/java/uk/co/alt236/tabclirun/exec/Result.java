package uk.co.alt236.tabclirun.exec;

import java.util.List;

public class Result {
    private final int exitCode;
    private final List<Line> lines;

    public Result(final int exitCode,
                  final List<Line> lines) {
        this.exitCode = exitCode;
        this.lines = lines;
    }

    public int getExitCode() {
        return exitCode;
    }

    public List<Line> getLines() {
        return lines;
    }
}
