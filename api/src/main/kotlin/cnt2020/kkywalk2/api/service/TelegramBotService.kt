package cnt2020.kkywalk2.api.service

import arrow.core.Either
import arrow.core.computations.either
import cnt2020.kkywalk2.api.common.repository.find
import cnt2020.kkywalk2.api.dto.AddBirthdayAlarmRequest
import cnt2020.kkywalk2.api.dto.BirthdayAlarmDto
import cnt2020.kkywalk2.api.dto.CreateRequest
import cnt2020.kkywalk2.api.dto.TelegramBotDto
import cnt2020.kkywalk2.api.entity.BirthdayAlarm
import cnt2020.kkywalk2.api.entity.TelegramBot
import cnt2020.kkywalk2.api.repository.BirthdayAlarmRepository
import cnt2020.kkywalk2.api.repository.TelegramBotRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TelegramBotService(
    private val telegramBotRepository: TelegramBotRepository,
    private val birthdayAlarmRepository: BirthdayAlarmRepository
) {

    fun findTelegramBots(): List<TelegramBotDto> {
        return telegramBotRepository.findAll().map { it.toDto() }
    }

    @Transactional
    fun create(createRequest: CreateRequest): TelegramBotDto {
        val telegramBot = TelegramBot(
            name = createRequest.name,
            token = createRequest.token
        )
        return telegramBotRepository.save(telegramBot).toDto()
    }

    fun findTelegramBot(id: Long): Either<Throwable, TelegramBotDto> {
        return telegramBotRepository.find(id).map { it.toDto() }
    }

    @Transactional
    fun addBirthdayAlarm(
        botId: Long,
        addBirthdayAlarmRequest: AddBirthdayAlarmRequest
    ): Either<Throwable, TelegramBotDto> {
        return either.eager {
            val telegramBot = telegramBotRepository.find(botId).bind()
            val birthdayAlarm = BirthdayAlarm(
                name = addBirthdayAlarmRequest.name,
                telegramBotId = telegramBot.id,
                birthdayDateTime = addBirthdayAlarmRequest.birthdayDateTime
            )
            birthdayAlarmRepository.save(birthdayAlarm)
            telegramBot.copy(birthdayAlarmList = telegramBot.birthdayAlarmList + listOf(birthdayAlarm)).toDto()
        }
    }

    private fun TelegramBot.toDto(): TelegramBotDto {
        return TelegramBotDto(this.id, this.name, this.token, this.updatedAt, this.birthdayAlarmList.map { it.toDto() })
    }

    private fun BirthdayAlarm.toDto(): BirthdayAlarmDto {
        return BirthdayAlarmDto(this.id, this.name, this.birthdayDateTime)
    }

}