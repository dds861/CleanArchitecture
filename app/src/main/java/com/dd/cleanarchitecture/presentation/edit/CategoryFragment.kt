package com.dd.cleanarchitecture.presentation.edit

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.dd.cleanarchitecture.R
import com.dd.core.data.Category
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.item_category.*

/**
 * A simple [Fragment] subclass.
 */
class CategoryFragment : Fragment() {

    private var categoryId = 0L
    private lateinit var viewModel: CategoryViewModel
    private var currentCategory = Category(key = 0L, name = "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        arguments?.let {
            categoryId = CategoryFragmentArgs.fromBundle(it).categoryId
        }

        if (categoryId != 0L) {
            viewModel.getCategory(categoryId)
        }

        checkButton.setOnClickListener {
            if (etCategoryKey.text.toString() != "" || etCategoryName.text.toString() != "") {
                currentCategory.key = etCategoryKey.text.toString().toLong()
                currentCategory.name = etCategoryName.text.toString()

                viewModel.saveCategory(currentCategory)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(etCategoryKey).popBackStack()

            } else {
                Toast.makeText(
                    context,
                    "Something went wrong, please try again",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })

        viewModel.currentCategory.observe(viewLifecycleOwner, Observer { category ->
            category?.let {
                currentCategory = it
                etCategoryKey.setText(it.key.toString(), TextView.BufferType.EDITABLE)
                etCategoryName.setText(it.name, TextView.BufferType.EDITABLE)
            }

        })

    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(etCategoryKey.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.category_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteCategory -> {
                if (context != null && categoryId != 0L) {
                    AlertDialog.Builder(context!!)
                        .setTitle("Remove category")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes") { dialog: DialogInterface?, which: Int ->
                            viewModel.deleteCategory(currentCategory)
                        }
                        .setNegativeButton("Cancel") { dialog: DialogInterface?, which: Int -> }
                        .create()
                        .show()
                }
            }
        }
        return true
    }
}
