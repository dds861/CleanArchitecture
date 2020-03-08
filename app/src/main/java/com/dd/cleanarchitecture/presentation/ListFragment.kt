package com.dd.cleanarchitecture.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dd.cleanarchitecture.R
import com.dd.cleanarchitecture.framework.CategoryViewModel
import com.dd.cleanarchitecture.framework.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    private val categoriesListAdapter = CategoriesListAdapter(arrayListOf())
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoriesListAdapter
        }

        addCategory.setOnClickListener { goToCategoryDetails() }

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.categories.observe(viewLifecycleOwner, Observer { categoriesList ->
            loadingView.visibility = View.GONE
            categoriesListView.visibility = View.VISIBLE
            categoriesListAdapter.updateCategories(categoriesList.sortedBy { it.name })
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCategories()
    }

    private fun goToCategoryDetails(id: Long = 0L) {
        val action = ListFragmentDirections.actionListFragmentToCategoryFragment()
        Navigation.findNavController(categoriesListView).navigate(action)
    }

}
