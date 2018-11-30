package uk.co.alt236.tabclirun.ui.views;

import uk.co.alt236.tabclirun.Command;
import uk.co.alt236.tabclirun.exec.Line;
import uk.co.alt236.tabclirun.exec.Result;
import uk.co.alt236.tabclirun.ui.implementations.ColorPane;

import javax.swing.*;
import java.awt.*;

class CommandTab extends JPanel {
    private static final Color COLOR_ERR = Color.decode("#E94F64");
    private static final Color COLOR_STD = Color.WHITE;
    private final JTextField textField;
    private final ColorPane textArea;
    private final FontProvider fontProvider;

    private Command command;

    CommandTab() {
        fontProvider = new FontProvider();
        textField = new JTextField();
        textArea = new ColorPane();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setupTextArea();

        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        textField.setEditable(false);

        add(textField);
        add(new JScrollPane(textArea));
    }

    void setCommand(final Command command) {
        this.command = command;

        textField.setText(command.getCommand());

        if (command.getBackgroundColor() != null) {
            textArea.setBackground(command.getBackgroundColor());
        }
    }

    void setResult(final Result result) {
        textArea.setEditable(true);

        for (final Line line : result.getLines()) {
            final Color color = line.isError()
                    ? getErrorColor()
                    : getTextColor();

            textArea.append(color, line.getText() + "\n");
        }

        textArea.setEditable(false);
    }

    private void setupTextArea() {
        Font defaultFont = UIManager.getDefaults().getFont("TextPane.font");
        int defaultSize = defaultFont.getSize();

        textArea.setFont(new Font(fontProvider.getMonospaceFontName(), Font.PLAIN, defaultSize));
        textArea.setEditable(false);
    }

    private Color getErrorColor() {
        return command.getErrorColor() == null
                ? COLOR_ERR
                : command.getErrorColor();
    }

    private Color getTextColor() {
        return command.getTextColor() == null
                ? COLOR_STD
                : command.getTextColor();
    }
}
