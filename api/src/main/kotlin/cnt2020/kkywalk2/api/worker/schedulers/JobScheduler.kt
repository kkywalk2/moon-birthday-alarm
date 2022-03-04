package cnt2020.kkywalk2.api.worker.schedulers

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecutionException
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class ExampleScheduler(
    private val job: Job? = null,
    private val jobLauncher: JobLauncher? = null
) {

    //@Scheduled(cron="0 20 3 * * *")
    @Scheduled(fixedDelay = 10000)
    fun executeJob() {
        try {
            if (job != null) {
                jobLauncher!!.run(
                    job,
                    JobParametersBuilder()
                        .addString("datetime", LocalDateTime.now().toString())
                        .toJobParameters() // job parameter 설정
                )
            }
        } catch (ex: JobExecutionException) {
            println(ex.message)
            ex.printStackTrace()
        }
    }
}