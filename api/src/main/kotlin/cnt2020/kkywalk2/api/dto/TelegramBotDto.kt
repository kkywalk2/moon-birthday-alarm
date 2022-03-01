package cnt2020.kkywalk2.api.dto

import java.time.LocalDateTime

data class TelegramBotDto(
    val id : Long = 0,
    val name: String = "",
    val token : String = "",
    val updatedAt : LocalDateTime = LocalDateTime.now(),
    val birthdayAlarmList: List<BirthdayAlarmDto> = listOf()
)