package cht2020.kkywalk2.api.entity

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

    @JoinColumn(name = "telegram_bot_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    val telegramBot: TelegramBot,

    @Column(name = "telegram_bot_id")
    val telegramBotId: Long = 0,

    @Column(name = "birthday_datetime")
    val birthdayDateTime: LocalDateTime,

    @field:CreationTimestamp
    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime
)