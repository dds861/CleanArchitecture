package com.dd.cleanarchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    val repository = CategoryRepository(RoomCategoryDataSource(application))

    val useCases = UseCases(
        AddCategory(repository),
        GetAllCategories(repository),
        GetCategory(repository),
        RemoveCategory(repository)
    )

    val saved = MutableLiveData<Boolean>()

    fun saveCategory(category: Category) {
        coroutineScope.launch {
            useCases.addCategory(category)
            saved.postValue(true)
        }
    }
}