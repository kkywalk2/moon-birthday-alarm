package cnt2020.kkywalk2.api.worker.tasklets

import cnt2020.kkywalk2.api.repository.BirthdayAlarmRepository
import cnt2020.kkywalk2.core.data.RedisBirthdayBotPacket
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.data.redis.connection.stream.ObjectRecord
import org.springframework.data.redis.connection.stream.RecordId
import org.springframework.data.redis.connection.stream.StreamRecords
import org.springframework.data.redis.core.RedisTemplate
import java.time.LocalDate
import java.time.LocalTime


class ProducerTask(
    private val birthdayAlarmRepository: BirthdayAlarmRepository,
    private val redisTemplate: RedisTemplate<String, Any>
) : Tasklet {

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        val localDateNow = LocalDate.now()
        val localDateTimeNowStart = localDateNow.atTime(LocalTime.MIN)
        val localDateTimeNowEnd = localDateNow.atTime(LocalTime.MAX)

        val birthdayAlarmList =
            birthdayAlarmRepository.findAllByBirthdayDateTimeBetween(localDateTimeNowStart, localDateTimeNowEnd)
        birthdayAlarmList.map {
            val record: ObjectRecord<String, RedisBirthdayBotPacket> = StreamRecords.newRecord()
                .`in`<String>("bot-stream")
                .ofObject<RedisBirthdayBotPacket>(
                    RedisBirthdayBotPacket(
                        it.name,
                        it.telegramBot?.token ?: "",
                        it.telegramBot?.chatId ?: "",
                        it.birthdayDateTime
                    )
                )
                .withId(RecordId.autoGenerate())

            //TODO : 전달관련 로그 추가 해야함
            val recordId: RecordId? = redisTemplate.opsForStream<String, RedisBirthdayBotPacket>()
                .add(record)

            println(recordId.toString())
        }

        return RepeatStatus.FINISHED
    }

}