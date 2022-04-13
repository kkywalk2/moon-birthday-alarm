package cnt2020.kkywalk2.api.repository

import cnt2020.kkywalk2.api.entity.BirthdayAlarm
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface BirthdayAlarmRepository : JpaRepository<BirthdayAlarm, Long> {

    fun findAllByBirthdayDateTimeBetween(start: LocalDateTime, end: LocalDateTime) : List<BirthdayAlarm>

    fun findByMonthAndDay(month: Int, day: Int) : List<BirthdayAlarm>

}