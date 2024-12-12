package dev.alt236.tabclirun.libs.exec;

import dev.alt236.tabclirun.libs.exec.result.Line;
import dev.alt236.tabclirun.libs.exec.result.Result;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandExecutor {

    public Result executeCommand(final String command) {
        final OutputCollector collector = new OutputCollector();
        final PumpStreamHandler streamHandler = new PumpStreamHandler(collector.getOutStream(), collector.getErrStream());
        final DefaultExecutor executor = new DefaultExecutor();


        final CommandLine commandline = CommandLine.parse(command);
        executor.setStreamHandler(streamHandler);

        return execute(executor, collector, commandline);
    }

    private Result execute(final DefaultExecutor executor,
                           final OutputCollector collector,
                           final CommandLine commandline) {
        try {
            final int exitCode = executor.execute(commandline);
            final List<Line> lines = collector.getLines();

            return new Result(exitCode, lines);
        } catch (Exception e) {
            return toResult(e);
        }
    }

    private Result toResult(final Exception e) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        final String stackTrace = sw.toString(); // stack trace as a string
        List<String> stringLines = Arrays.asList(stackTrace.split("\\r?\\n"));

        final List<Line> lines = stringLines.stream()
                .map(string -> new Line(string, true))
                .collect(Collectors.toList());

        return new Result(-1, lines);

    }
}