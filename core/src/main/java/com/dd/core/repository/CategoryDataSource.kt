package com.dd.core.repository

import com.dd.core.data.Category

interface CategoryDataSource {
    suspend fun add(category: Category)

    suspend fun get(id: Long): Category?

    suspend fun getAll(): List<Category>

    suspend fun remove(category: Category)
}