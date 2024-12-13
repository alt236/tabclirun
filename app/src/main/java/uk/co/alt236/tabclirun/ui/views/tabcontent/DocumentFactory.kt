package uk.co.alt236.tabclirun.ui.views.tabcontent

import dev.alt236.tabclirun.libs.exec.result.CommandOutput
import javax.swing.text.AttributeSet
import javax.swing.text.DefaultStyledDocument
import javax.swing.text.Document
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants

internal class DocumentFactory(private val colors: CommandColors) {
    private val attrNormalText by lazy {
        val attr = SimpleAttributeSet()
        attr.addAttribute(StyleConstants.Foreground, colors.textColor)
        attr
    }

    private val attrErrorText by lazy {
        val attr = SimpleAttributeSet()
        attr.addAttribute(StyleConstants.Foreground, colors.errorColor)
        attr
    }

    fun getDocument(commandOutput: CommandOutput): DocumentWrapper {
        return when {
            commandOutput.isOnlyStdOut -> createSolidDocument(attrNormalText, commandOutput)
            commandOutput.isOnlyStdErr -> createSolidDocument(attrErrorText, commandOutput)
            else -> createMixedDocument(commandOutput)
        }
    }

    private fun createMixedDocument(commandOutput: CommandOutput): DocumentWrapper {
        val doc = DefaultStyledDocument()

        // TODO: this can be optimised to work in attr based chunks
        for (line in commandOutput.lines) {
            if (line.isError) {
                doc.insertString(doc.length, line.text + "\n", attrErrorText)
            } else {
                doc.insertString(doc.length, line.text + "\n", attrNormalText)
            }
        }

        return DocumentWrapper(doc, "mixed")
    }


    private fun createSolidDocument(textAttr: AttributeSet, commandOutput: CommandOutput): DocumentWrapper {
        val doc = DefaultStyledDocument()

        val sb = StringBuilder()
        for (line in commandOutput.lines) {
            sb.appendLine(line.text)
        }

        doc.insertString(doc.length, sb.toString(), textAttr)
        return DocumentWrapper(doc, "solid")
    }

    data class DocumentWrapper(val document: Document, val method: String)
}