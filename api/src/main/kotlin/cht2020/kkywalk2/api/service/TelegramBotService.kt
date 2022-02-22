package cht2020.kkywalk2.api.service

import cnt2020.kkywalk2.core.entity.TelegramBot
import cnt2020.kkywalk2.core.repository.TelegramBotRepository
import org.springframework.stereotype.Service

@Service
class TelegramBotService(
    private val telegramBotRepository: TelegramBotRepository
) {

    fun get() : List<TelegramBot> {
        return telegramBotRepository.findAll()
    }

}