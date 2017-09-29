package brain

import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.exceptions.TelegramApiException
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import javax.print.PrintException
import javax.print.DocPrintJob
import javax.print.PrintServiceLookup
import javax.print.attribute.standard.PageRanges
import javax.print.attribute.HashPrintRequestAttributeSet
import javax.print.attribute.PrintRequestAttributeSet



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

    private fun checkAuthorize(chatId: Long): Boolean {
        if (chatId == 259558114.toLong() /*|| chatId ==*/ ) {
            return true
        } else {
            return false
        }
    }

    private fun sendMessage(chatId: Long, msg: String) {
        val message = SendMessage()
                .setChatId(chatId)
                .setText(msg + chatId)
        try {
            sendMessage(message) // Call method to send the message
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }

    private fun printCommand() {
        val aset = HashPrintRequestAttributeSet()
        aset.add(PageRanges(1, 5))
        val services = PrintServiceLookup.lookupPrintServices(myFormat, aset)
        if (services.isNotEmpty()) {
            val job = services[0].createPrintJob()
            try {
                job.print(myDoc, aset)
            } catch (pe: PrintException) {
                pe.printStackTrace()
            }
        }
    }

}