package uk.co.alt236.tabclirun.ui.views.tabcontent

import dev.alt236.tabclirun.libs.config.Command
import dev.alt236.tabclirun.libs.exec.result.Result
import uk.co.alt236.tabclirun.ui.implementations.ColorPane
import uk.co.alt236.tabclirun.ui.views.common.FontProvider
import java.awt.Dimension
import java.awt.Font
import javax.swing.BoxLayout
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextField
import javax.swing.UIManager

internal class CommandTab : JPanel() {
    private val textField = JTextField()
    private val textArea = ColorPane()
    private val fontProvider = FontProvider()

    private lateinit var command: Command
    private lateinit var colors: CommandColors


    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        setupTextArea()

        textField.maximumSize = Dimension(Int.MAX_VALUE, textField.preferredSize.height)
        textField.isEditable = false

        add(textField)
        add(JScrollPane(textArea))
    }

    fun setCommand(command: Command) {
        this.command = command
        this.colors = CommandColors(command)

        textField.text = command.command

        if (command.backgroundColor != null) {
            textArea.background = command.backgroundColor
        }
    }

    fun setResult(result: Result) {
        textArea.isEditable = true

        for (line in result.lines) {
            val color = colors.getColor(line)
            textArea.append(color, line.text + "\n")
        }

        if (command.isDisableAutoScroll && textArea.text.isNotEmpty()) {
            textArea.caretPosition = 0
        }

        textArea.isEditable = false
    }

    private fun setupTextArea() {
        val defaultFont = UIManager.getDefaults().getFont("TextPane.font")
        val defaultSize = defaultFont.size

        textArea.font = Font(fontProvider.monospaceFontName, Font.PLAIN, defaultSize)
        textArea.isEditable = false
    }
}