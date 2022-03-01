package cnt2020.kkywalk2.api.dto

import java.time.LocalDateTime

data class RedisBirthdayBotPacket (
    val name: String = "",
    val token: String = "",
    val birthdayDateTime: LocalDateTime = LocalDateTime.now(),
)