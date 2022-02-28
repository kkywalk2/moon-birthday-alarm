package cnt2020.kkywalk2.api.controller

import arrow.core.Either
import cnt2020.kkywalk2.api.dto.CreateRequest
import cnt2020.kkywalk2.api.dto.CreateResponse
import cnt2020.kkywalk2.api.dto.FindBookResponse
import cnt2020.kkywalk2.api.dto.FindBooksResponse
import cnt2020.kkywalk2.api.service.TelegramBotService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("telegram-bot")
class TelegramBotController(
    private val telegramBotService: TelegramBotService
) {

    @PostMapping
    fun create(@RequestBody createRequest: CreateRequest): CreateResponse {
        return telegramBotService.create(createRequest)
    }

    @GetMapping
    fun findBooks(): FindBooksResponse {
        return telegramBotService.findTelegramBots()
    }

    @GetMapping("/{id}")
    fun findBook(@PathVariable id: Long): Either<Throwable, FindBookResponse> {
        return telegramBotService.findTelegramBot(id)
    }

}