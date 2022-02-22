package cnt2020.kkywalk2.core.repository;

import cnt2020.kkywalk2.core.entity.BirthdayAlarm
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BirthdayAlarmRepository : JpaRepository<BirthdayAlarm, Long> {
}