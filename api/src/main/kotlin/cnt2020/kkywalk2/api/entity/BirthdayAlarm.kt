package cnt2020.kkywalk2.api.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "birthday_alarm")
class BirthdayAlarm(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    val id: Long = 0,

    @Column(name = "name")
    val name: String,

    @JoinColumn(name = "telegram_bot_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    val telegramBot: TelegramBot? = null,

    @Column(name = "telegram_bot_id")
    val telegramBotId: Long = 0,

    @Deprecated("change to month and day")
    @Column(name = "birthday_datetime")
    val birthdayDateTime: LocalDateTime,

    @Column(name = "month")
    val month: Int,

    @Column(name = "day")
    val day: Int,

    @Column(name = "is_moon_birthday")
    val isMoonBirthDay: Boolean,

    @field:CreationTimestamp
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
)