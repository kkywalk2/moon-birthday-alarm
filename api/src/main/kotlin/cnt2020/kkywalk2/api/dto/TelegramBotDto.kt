package cnt2020.kkywalk2.api.dto

import cnt2020.kkywalk2.api.entity.BirthdayAlarm
import java.time.LocalDateTime

data class TelegramBotDto(
    val id : Long,
    val name: String,
    val token : String,
    val updatedAt : LocalDateTime,
    val birthdayAlarmList: List<BirthdayAlarm>
)