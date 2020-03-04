package com.dd.cleanarchitecture.framework

import com.dd.core.usecase.AddCategory
import com.dd.core.usecase.GetAllCategories
import com.dd.core.usecase.GetCategory
import com.dd.core.usecase.RemoveCategory

data class UseCases(
    val addCategory: AddCategory,
    val getAllCategories: GetAllCategories,
    val getCategory: GetCategory,
    val removeCategory: RemoveCategory
)