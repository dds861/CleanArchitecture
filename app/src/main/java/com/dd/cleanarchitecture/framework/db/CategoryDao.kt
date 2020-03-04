package com.dd.cleanarchitecture.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface CategoryDao {
    @Insert(onConflict = REPLACE)
    suspend fun addCategoryEntity(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getCategoryEntity(id: Long): CategoryEntity?

    @Query("SELECT * FROM category")
    suspend fun getAllCategoryEntities(): List<CategoryEntity>

    @Delete
    suspend fun deleteCategoryEntity(categoryEntity: CategoryEntity)
}