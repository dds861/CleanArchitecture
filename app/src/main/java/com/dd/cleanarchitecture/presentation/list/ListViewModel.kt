package com.dd.cleanarchitecture.presentation.list

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

class ListViewModel(application: Application) : AndroidViewModel(application) {
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

    val categories = MutableLiveData<List<Category>>()

    fun getCategories() {
        coroutineScope.launch {
            val categoriesList = useCases.getAllCategories()
            categories.postValue(categoriesList)
        }
    }

}