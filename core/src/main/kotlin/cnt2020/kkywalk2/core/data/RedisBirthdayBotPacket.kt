package cnt2020.kkywalk2.core.data

import java.time.LocalDateTime

data class RedisBirthdayBotPacket(
    val name: String = "",
    val token: String = "",
    val chatId: String = "",
    val month: Int = 1,
    val day: Int = 1,

    @Deprecated("change to month and day")
    val birthdayDateTime: LocalDateTime = LocalDateTime.now(),
)
