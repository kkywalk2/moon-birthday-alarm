package cnt2020.kkywalk2.api.common.repository

import arrow.core.Either
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.EntityNotFoundException

fun <E,ID> JpaRepository<E, ID>.find(id: ID?) : Either<Throwable, E> {
    val notNullId = id ?: return Either.Left(NullPointerException())
    val entity = this.findById(notNullId)

    return if(entity.isPresent) Either.Right(entity.get()) else Either.Left(EntityNotFoundException())
}