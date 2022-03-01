package cnt2020.kkywalk2.api.controller

import arrow.core.Either
import cnt2020.kkywalk2.api.dto.*
import cnt2020.kkywalk2.api.service.TelegramBotService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("telegram-bot")
class TelegramBotController(
    private val telegramBotService: TelegramBotService
) {

    @PostMapping
    fun create(@RequestBody request: CreateRequest): CreateResponse {
        return telegramBotService.create(request)
    }

    @GetMapping
    fun findTelegramBots(): FindTelegramBotsResponse {
        return telegramBotService.findTelegramBots()
    }

    @GetMapping("/{id}")
    fun findTelegramBot(@PathVariable id: Long): Either<Throwable, FindTelegramBotResponse> {
        return telegramBotService.findTelegramBot(id)
    }

    @PostMapping("/{id}/birthday-alarm")
    fun addBirthDayAlarm(@PathVariable id: Long, @RequestBody request: AddBirthdayAlarmRequest): Either<Throwable, AddBirthdayAlarmResponse> {
        return telegramBotService.addBirthdayAlarm(id, request)
    }

}