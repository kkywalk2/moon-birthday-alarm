package cht2020.kkywalk2.api.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "telegram_bot")
class TelegramBot(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    val id: Long = 0,

    @Column(name = "name")
    val name: String,

    @Column(name = "token")
    val token: String,

    @field:CreationTimestamp
    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime,

    @OneToMany(mappedBy = "telegramBot")
    val birthdayAlarmList: List<BirthdayAlarm>
)