package com.project.OrderFood.responsitory

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseRepository<T : Any, ID> : MongoRepository<T, ID> {
    override fun findAll(): List<T>

    fun getById(id: ID): T

    override fun <S : T?> insert(entities: Iterable<S>): List<S>

    override fun delete(entity: T)
}