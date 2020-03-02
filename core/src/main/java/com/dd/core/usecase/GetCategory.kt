package com.dd.core.usecase

import com.dd.core.repository.CategoryRepository

class GetCategory(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(id: Long) = categoryRepository.getCategory(id)
}