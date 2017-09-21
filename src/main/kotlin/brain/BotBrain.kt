package brain

import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.exceptions.TelegramApiException
import org.telegram.telegrambots.api.methods.send.SendMessage

class BotBrain : TelegramLongPollingBot() {

    override fun getBotUsername() = brain.botUsername()

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val message = SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.message.chatId)
                    .setText("${update.message.text} ${update.message.chatId}")
            try {
                sendMessage(message) // Call method to send the message
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }

        }
    }

    override fun getBotToken() = brain.botToken()

}