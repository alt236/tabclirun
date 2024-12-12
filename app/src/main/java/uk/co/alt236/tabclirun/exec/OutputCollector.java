package uk.co.alt236.tabclirun.exec;

import org.apache.commons.exec.LogOutputStream;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class OutputCollector {
    private final List<Line> lines;
    private final OutputStream outStream;
    private final OutputStream errStream;

    OutputCollector() {
        lines = Collections.synchronizedList(new ArrayList<>());
        outStream = new CollectingLogOutputStream(lines, false);
        errStream = new CollectingLogOutputStream(lines, true);
    }

    OutputStream getOutStream() {
        return outStream;
    }

    OutputStream getErrStream() {
        return errStream;
    }

    final List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }


    private static class CollectingLogOutputStream extends LogOutputStream {
        private final List<Line> lines;
        private final boolean error;

        CollectingLogOutputStream(List<Line> lines, boolean error) {
            this.lines = lines;
            this.error = error;
        }

        @Override
        protected void processLine(String line, int level) {
            lines.add(new Line(line, error));
        }
    }
}
