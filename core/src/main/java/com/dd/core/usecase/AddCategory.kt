package com.dd.core.usecase

import com.dd.core.data.Category
import com.dd.core.repository.CategoryRepository

class AddCategory(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(category: Category) = categoryRepository.addNote(category)
}