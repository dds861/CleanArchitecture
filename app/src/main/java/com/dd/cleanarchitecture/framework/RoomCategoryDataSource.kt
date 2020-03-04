package com.dd.cleanarchitecture.framework

import android.content.Context
import com.dd.cleanarchitecture.framework.db.CategoryEntity
import com.dd.cleanarchitecture.framework.db.DatabaseService
import com.dd.core.data.Category
import com.dd.core.repository.CategoryDataSource

class RoomCategoryDataSource(context: Context):CategoryDataSource {
    private val categoryDao = DatabaseService.getInstance(context).categoryDao()

    override suspend fun add(category: Category) = categoryDao.addCategoryEntity(CategoryEntity.fromCategory(category))

    override suspend fun get(id: Long): Category? = categoryDao.getCategoryEntity(id)?.toCategory()

    override suspend fun getAll(): List<Category> = categoryDao.getAllCategoryEntities().map { it.toCategory() }

    override suspend fun remove(category: Category) = categoryDao.deleteCategoryEntity(CategoryEntity.fromCategory(category))
}