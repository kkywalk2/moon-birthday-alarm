package cnt2020.kkywalk2.api.dto

import java.time.LocalDateTime

data class BirthdayAlarmDto(
    val id : Long = 0,
    val name : String = "",
    val birthdayDateTime: LocalDateTime = LocalDateTime.now()
)
