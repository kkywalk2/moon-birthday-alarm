package cnt2020.kkywalk2.api.controller

import cnt2020.kkywalk2.api.dto.CreateRequest
import cnt2020.kkywalk2.api.dto.CreateResponse
import cnt2020.kkywalk2.api.service.TelegramBotService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("telegram-bot")
class TelegramBotController(
    private val telegramBotService: TelegramBotService
) {

    @PostMapping
    fun create(@RequestBody createRequest: CreateRequest): CreateResponse {
        return telegramBotService.create(createRequest)
    }

}