package com.dd.cleanarchitecture.presentation.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dd.cleanarchitecture.framework.RoomCategoryDataSource
import com.dd.cleanarchitecture.framework.UseCases
import com.dd.core.data.Category
import com.dd.core.repository.CategoryRepository
import com.dd.core.usecase.AddCategory
import com.dd.core.usecase.GetAllCategories
import com.dd.core.usecase.GetCategory
import com.dd.core.usecase.RemoveCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val repository = CategoryRepository(
        RoomCategoryDataSource(
            application
        )
    )

    val useCases = UseCases(
        AddCategory(repository),
        GetAllCategories(repository),
        GetCategory(repository),
        RemoveCategory(repository)
    )

    val saved = MutableLiveData<Boolean>()
    val currentCategory = MutableLiveData<Category?>()


    fun saveCategory(category: Category) {
        coroutineScope.launch {
            useCases.addCategory(category)
            saved.postValue(true)
        }
    }

    fun getCategory(id: Long) {
        coroutineScope.launch {
            val category = useCases.getCategory(id)
            currentCategory.postValue(category)
        }
    }

    fun deleteCategory(category: Category) {
        coroutineScope.launch {
            useCases.removeCategory(category)
            saved.postValue(true)
        }
    }

}