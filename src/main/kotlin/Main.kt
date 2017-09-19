import brain.BotBrain
import org.telegram.telegrambots.exceptions.TelegramApiException
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.ApiContextInitializer

fun main(args: Array<String>) {

    ApiContextInitializer.init()

    val botsApi = TelegramBotsApi()

    try {
        botsApi.registerBot(BotBrain())
    } catch (e: TelegramApiException) {
        e.printStackTrace()
    }


}