package com.dd.cleanarchitecture.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dd.core.data.Category

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "category_key")
    val key: Long,

    @ColumnInfo(name = "category_name")
    val name: String

) {
    companion object {
        fun fromCategory(category: Category) =
            CategoryEntity(key = category.key, name = category.name)
    }

    fun toCategory() = Category(id, key, name)
}