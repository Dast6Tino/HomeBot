package brain

import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.exceptions.TelegramApiException
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message

class BotBrain : TelegramLongPollingBot() {
    private val errorMessage = "Что то пошло не так."

    override fun getBotUsername() = brain.botUsername()

    override fun getBotToken() = brain.botToken()

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) {
            when {
                update.message.hasText() -> {
                    commandCheck(update.message)
                    /*val message = SendMessage() // Create a SendMessage object with mandatory fields
                            .setChatId(update.message.chatId)
                            .setText("${update.message.text} ${update.message.chatId}")
                    try {
                        sendMessage(message) // Call method to send the message
                    } catch (e: TelegramApiException) {
                        e.printStackTrace()
                    }*/
                }
                update.message.hasDocument() -> {

                }
                update.message.hasPhoto() -> {

                }
            }
        }
    }

    private fun commandCheck(msg: Message) {
        when (msg.text) {
            "/start" -> {

            }
            "/help" -> {

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

}