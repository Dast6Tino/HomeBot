package brain

import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.exceptions.TelegramApiException
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Document
import org.telegram.telegrambots.api.objects.Message
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import javax.print.PrintException
import javax.print.PrintServiceLookup
import javax.print.attribute.standard.PageRanges
import javax.print.attribute.HashPrintRequestAttributeSet
import javax.swing.JFileChooser

class BotBrain : TelegramLongPollingBot() {
    private val helpMessage = "Печать -- /print\nСохранить файл -- /save\nПоказать последние файлы -- /show"
    private val startMessage = "Подсказка по командам -- /help\n$helpMessage"
    private val errorMessage = "Что то пошло не так."
    private val errorLoginMessage = "Вы не авторизованны."

    override fun getBotUsername() = brain.botUsername()

    override fun getBotToken() = brain.botToken()

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) {
            if (checkAuthorize(update.message.chatId)) {
                when {
                    update.message.hasText() -> {
                        commandCheck(update.message)
                    }
                    update.message.hasDocument() -> {
                        commandPrint(update.message.chatId, update.message.document)
                        commandSave(update.message.document)
                    }
                    update.message.hasPhoto() -> {

                    }
                }
            } else {
                sendMessage(update.message.chatId, errorLoginMessage)
                println(update.message.chatId)
            }
        }
    }

    private fun commandCheck(msg: Message) {
        when (msg.text) {
            "/start" -> {
                sendMessage(msg.chatId, startMessage)
            }
            "/help" -> {
                sendMessage(msg.chatId, helpMessage)
            }
            "/print" -> {

            }
            "/save" -> {

            }
            "/show" -> {

            }
            else -> {
                sendMessage(msg.chatId, errorMessage)
            }
        }
    }

    private fun checkAuthorize(chatId: Long) = chatId == 259558114.toLong() || chatId == 72836827.toLong()

    private fun sendMessage(chatId: Long, msg: String) {
        val message = SendMessage()
                .setChatId(chatId)
                .setText(msg)
        try {
            sendMessage(message) // Call method to send the message
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }

    private fun commandPrint(chatId: Long, document: Document) {
        val aset = HashPrintRequestAttributeSet()

        sendMessage(chatId, document.toString())

        /*aset.add(PageRanges(1, 5))
        val services = PrintServiceLookup.lookupPrintServices(myFormat, aset)
        if (services.isNotEmpty()) {
            val job = services[0].createPrintJob()
            try {
                job.print(document, aset)
            } catch (pe: PrintException) {
                pe.printStackTrace()
            }
        }*/
    }

    private fun commandSave(file: Any) {

        /*val fc = JFileChooser()
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                val fileStream = FileOutputStream(fc.selectedFile)
                val os = ObjectOutputStream(fileStream)
                os.writeObject(file)
                os.close()
            }
            catch (e: Exception) {
                println("Что-то пошло не так...")
            }
        }*/
    }
}