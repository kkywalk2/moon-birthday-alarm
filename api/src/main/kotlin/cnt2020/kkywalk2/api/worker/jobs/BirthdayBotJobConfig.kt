package cnt2020.kkywalk2.api.worker.jobs

import cnt2020.kkywalk2.api.repository.BirthdayAlarmRepository
import cnt2020.kkywalk2.api.worker.tasklets.ProducerTask
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.RedisTemplate


@Configuration
class ExampleConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val redisTemplate: RedisTemplate<String, Any>,
    private val birthdayAlarmRepository: BirthdayAlarmRepository
) {

    @Bean
    fun tutorialJob(): Job {
        return jobBuilderFactory["botJob"]
            .start(tutorialStep()) // Step 설정
            .build()
    }

    @Bean
    fun tutorialStep(): Step {
        return stepBuilderFactory["botStep"]
            .tasklet(ProducerTask(birthdayAlarmRepository, redisTemplate))
            .build()
    }

}