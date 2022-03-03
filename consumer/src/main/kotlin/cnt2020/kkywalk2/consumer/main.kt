package cnt2020.kkywalk2.consumer

import io.lettuce.core.*
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands

fun main(args: Array<String>) {
    val redisClient: RedisClient = RedisClient.create("redis://localhost:6379")

    val connection: StatefulRedisConnection<String, String> = redisClient.connect()
    val syncCommands: RedisCommands<String, String> = connection.sync()

    try {
        syncCommands.xgroupCreate(XReadArgs.StreamOffset.from("bot-stream","0-0"), "bot-group")
    } catch (redisBusyException: RedisBusyException) {
        println(String.format("\t Group '%s' already exists", "bot-stream"))
    }

    while (true) {
        val messages: MutableList<StreamMessage<String, String>> = syncCommands.xreadgroup(
            Consumer.from("bot-group", "consumer_1"),
            XReadArgs.StreamOffset.lastConsumed("bot-stream")
        )
        if (messages.isNotEmpty()) {
            for (message in messages) {
                println(message)
                // Confirm that the message has been processed using XACK
                syncCommands.xack("bot-stream", "application_1", message.id)
            }
        }
    }

    connection.close();
    redisClient.shutdown();
}