package cnt2020.kkywalk2.api.dto

import java.time.LocalDateTime

data class BirthdayAlarmDto(
    val id : Long = 0,
    val name : String = "",
    val month : Int = 1,
    val day : Int = 1,
    val isMoonBirthday : Boolean = false,
    
    @Deprecated("change to month and day")
    val birthdayDateTime: LocalDateTime = LocalDateTime.now()
)
