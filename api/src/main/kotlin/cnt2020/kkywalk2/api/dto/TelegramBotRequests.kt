package cnt2020.kkywalk2.api.dto

import java.time.LocalDateTime

data class CreateRequest(
    val name: String,
    val token: String,
    val chatId: String,
)

data class AddBirthdayAlarmRequest(
    val name : String,
    val month : Int,
    val day : Int,
    val isMoonBirthday : Boolean,

    @Deprecated("change to month and day")
    val birthdayDateTime: LocalDateTime = LocalDateTime.now()
)