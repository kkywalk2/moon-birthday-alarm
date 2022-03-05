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
import java.time.LocalDateTime

suspend fun main(args: Array<String>) {
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
        val messages: MutableList<StreamMessage<String, String>> = syncCommands.xreadgroup(
            Consumer.from("bot-group", "consumer_1"),
            XReadArgs.StreamOffset.lastConsumed("bot-stream")
        )
        if (messages.isNotEmpty()) {
            for (message in messages) {
                syncCommands.xack("bot-stream", "application_1", message.id)

                val birthdayBotPacket = RedisBirthdayBotPacket(
                    name = message.body["name"]!!,
                    token = message.body["token"]!!,
                    birthdayDateTime = LocalDateTime.parse(message.body["birthdayDateTime"])
                )
                println(birthdayBotPacket)


                client.request<HttpResponse>("https://api.telegram.org/bot${birthdayBotPacket.token}/sendmessage?") {
                    method = HttpMethod.Get
                    parameter("text", birthdayBotPacket.name)
                    parameter("chat_id", birthdayBotPacket.chatId)
                }
            }
        }
    }

    client.close()
    connection.close()
    redisClient.shutdown()
}