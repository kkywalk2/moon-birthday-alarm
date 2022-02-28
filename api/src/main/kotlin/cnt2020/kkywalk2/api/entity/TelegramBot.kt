package cnt2020.kkywalk2.api.entity

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

    @Column(name = "name", unique = true)
    val name: String,

    @Column(name = "token")
    val token: String,

    @field:CreationTimestamp
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "telegramBot", fetch = FetchType.LAZY)
    val birthdayAlarmList: List<BirthdayAlarm> = listOf()
)