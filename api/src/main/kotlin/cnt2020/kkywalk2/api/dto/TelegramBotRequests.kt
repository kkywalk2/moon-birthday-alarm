package cnt2020.kkywalk2.api.dto

import java.time.LocalDateTime

data class CreateRequest(
    val name: String = "",
    val token: String = "",
    val chatId: String = "",
)

data class AddBirthdayAlarmRequest(
    val name : String = "",
    val birthdayDateTime: LocalDateTime = LocalDateTime.now()
)