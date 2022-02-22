package cnt2020.kkywalk2.core.repository;

import cnt2020.kkywalk2.core.entity.TelegramBot
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TelegramBotRepository : JpaRepository<TelegramBot, Long> {
}