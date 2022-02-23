package cht2020.kkywalk2.api.service

import cht2020.kkywalk2.api.entity.TelegramBot
import cht2020.kkywalk2.api.repository.TelegramBotRepository
import org.springframework.stereotype.Service

@Service
class TelegramBotService(
    private val telegramBotRepository: TelegramBotRepository
) {

    fun get() : List<TelegramBot> {
        return telegramBotRepository.findAll()
    }

}