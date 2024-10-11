package com.project.order.responsitory.base

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseRepository<T : Any, ID> : MongoRepository<T, ID> {
    override fun findAll(): List<T>

    override fun <S : T?> insert(entities: Iterable<S>): List<S>

    override fun delete(entity: T)

    fun getById(id: ID): T

    override fun <S : T> save(entity: S): S

    override fun <S : T> saveAll(entities: MutableIterable<S>): MutableList<S>
}