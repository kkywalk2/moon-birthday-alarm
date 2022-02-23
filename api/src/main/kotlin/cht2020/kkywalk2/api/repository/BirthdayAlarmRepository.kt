package cht2020.kkywalk2.api.repository

import cht2020.kkywalk2.api.entity.BirthdayAlarm
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BirthdayAlarmRepository : JpaRepository<BirthdayAlarm, Long> {
}