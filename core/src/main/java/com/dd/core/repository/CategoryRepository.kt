package com.dd.core.repository

import com.dd.core.data.Category

class CategoryRepository(private val categoryDataSource: CategoryDataSource) {

    suspend fun addNote(category: Category) = categoryDataSource.add(category)

    suspend fun getCategory(id: Long) = categoryDataSource.get(id)

    suspend fun getAllCategories() = categoryDataSource.getAll()

    suspend fun removeCategory(category: Category) = categoryDataSource.remove(category)

}