package com.dd.core.usecase

import com.dd.core.repository.CategoryRepository

class GetAllCategories(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke() = categoryRepository.getAllCategories()
}