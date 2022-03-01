package cnt2020.kkywalk2.api.service

import cnt2020.kkywalk2.api.dto.BirthdayAlarmDto
import cnt2020.kkywalk2.api.entity.BirthdayAlarm
import cnt2020.kkywalk2.api.repository.BirthdayAlarmRepository
import org.springframework.stereotype.Service

@Service
class BirthdayAlarmService(
    private val birthdayAlarmRepository: BirthdayAlarmRepository
) {

    fun findBirthdayAlarms(): List<BirthdayAlarmDto> {
        return birthdayAlarmRepository.findAll().map { it.toDto() }
    }

    private fun BirthdayAlarm.toDto(): BirthdayAlarmDto {
        return BirthdayAlarmDto(this.id, this.name, this.birthdayDateTime)
    }

}