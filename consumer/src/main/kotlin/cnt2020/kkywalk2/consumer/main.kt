package cnt2020.kkywalk2.consumer

import cnt2020.kkywalk2.core.data.RedisBirthdayBotPacket
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.lettuce.core.*
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

fun main(args: Array<String>) {
    val redisClient: RedisClient = RedisClient.create("redis://localhost:6379")
    val client = HttpClient(CIO)

    val connection: StatefulRedisConnection<String, String> = redisClient.connect()
    val syncCommands: RedisCommands<String, String> = connection.sync()

    try {
        syncCommands.xgroupCreate(XReadArgs.StreamOffset.from("bot-stream", "0-0"), "bot-group")
    } catch (redisBusyException: RedisBusyException) {
        println(String.format("\t Group '%s' already exists", "bot-group"))
    }

    while (true) {
        Observable.fromCallable {
            syncCommands.xreadgroup(
                Consumer.from("bot-group", "consumer_1"),
                XReadArgs.StreamOffset.lastConsumed("bot-stream")
            ) }
            .filter { it.isNotEmpty() }
            .observeOn(Schedulers.io())
            .subscribeBy(
                onNext = { list ->
                    list.map {
                            syncCommands.xack("bot-stream", "application_1", it.id)
                            sendTelegramMessage(client, it)
                                .subscribeBy(
                                    onNext = { response -> println(response) },
                                    onError = { throwable -> throwable.printStackTrace() }
                                )
                    }
                },
                onError = { it.printStackTrace() }
            )
    }

    client.close()
    connection.close()
    redisClient.shutdown()
}

fun sendTelegramMessage(
    client: HttpClient,
    message: StreamMessage<String, String>
): Observable<HttpResponse> {
    return Observable.fromCallable {
        val birthdayBotPacket = RedisBirthdayBotPacket(
            name = message.body["name"]!!,
            token = message.body["token"]!!,
            birthdayDateTime = LocalDateTime.parse(message.body["birthdayDateTime"])
        )

        runBlocking {
            client.request<HttpResponse>("https://api.telegram.org/bot${birthdayBotPacket.token}/sendmessage?") {
                method = HttpMethod.Get
                parameter("text", birthdayBotPacket.name)
                parameter("chat_id", birthdayBotPacket.chatId)
            }
        }
    }
}