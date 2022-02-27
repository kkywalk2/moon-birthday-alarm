package cnt2020.kkywalk2.api.service

import cnt2020.kkywalk2.api.dto.CreateRequest
import cnt2020.kkywalk2.api.dto.TelegramBotDto
import cnt2020.kkywalk2.api.entity.TelegramBot
import cnt2020.kkywalk2.api.repository.TelegramBotRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TelegramBotService(
    private val telegramBotRepository: TelegramBotRepository
) {

    fun get(): List<TelegramBot> {
        return telegramBotRepository.findAll()
    }

    @Transactional
    fun create(createRequest: CreateRequest): TelegramBotDto {
        val telegramBot = TelegramBot(
            name = createRequest.name,
            token = createRequest.token
        )
        return telegramBotRepository.save(telegramBot).toDto()
    }

    private fun TelegramBot.toDto(): TelegramBotDto {
        return TelegramBotDto(this.id, this.name, this.token, this.updatedAt, this.birthdayAlarmList)
    }

}