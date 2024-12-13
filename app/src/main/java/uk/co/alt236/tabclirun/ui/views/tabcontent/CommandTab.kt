package uk.co.alt236.tabclirun.ui.views.tabcontent

import dev.alt236.tabclirun.libs.config.Command
import dev.alt236.tabclirun.libs.exec.result.CommandOutput
import uk.co.alt236.tabclirun.ui.implementations.ColorPane
import uk.co.alt236.tabclirun.ui.views.common.FontProvider
import java.awt.Dimension
import java.awt.Font
import javax.swing.BoxLayout
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextField
import javax.swing.UIManager

internal class CommandTab(verbose: Boolean) : JPanel() {
    private val commandNameField = JTextField()
    private val commandOutputField = ColorPane()
    private val fontProvider = FontProvider()
    private val resultBinder = ResultBinder(commandOutputField, verbose)

    private lateinit var command: Command
    private lateinit var colors: CommandColors


    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)

        setupTextArea()

        commandNameField.maximumSize = Dimension(Int.MAX_VALUE, commandNameField.preferredSize.height)
        commandNameField.isEditable = false

        add(commandNameField)
        add(JScrollPane(commandOutputField))
    }

    fun setCommand(command: Command) {
        this.command = command
        this.colors = CommandColors(command)

        commandNameField.text = command.command

        if (command.backgroundColor != null) {
            commandOutputField.background = command.backgroundColor
        }
    }

    fun setResult(commandOutput: CommandOutput) {
        commandOutputField.isEditable = true

        resultBinder.bind(colors, commandOutput)

        if (command.isDisableAutoScroll && commandOutputField.text.isNotEmpty()) {
            commandOutputField.caretPosition = 0
        }

        commandOutputField.isEditable = false
    }

    private fun setupTextArea() {
        val defaultFont = UIManager.getDefaults().getFont("TextPane.font")
        val defaultSize = defaultFont.size

        commandOutputField.font = Font(fontProvider.monospaceFontName, Font.PLAIN, defaultSize)
        commandOutputField.isEditable = false
    }

}