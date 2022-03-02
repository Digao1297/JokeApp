package br.com.digao1297.jokerapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.digao1297.jokerapp.R
import br.com.digao1297.jokerapp.data.CategoryRemoteDataSource
import br.com.digao1297.jokerapp.model.Category
import br.com.digao1297.jokerapp.presentation.HomePresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieAdapter

class HomeFragment : Fragment() {

    private lateinit var presenter: HomePresenter
    private val adapter = GroupieAdapter()
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this, CategoryRemoteDataSource())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        progressBar = view.findViewById(R.id.progress_bar)

        val recycleView = view.findViewById<RecyclerView>(R.id.rv_home)
        recycleView.layoutManager = LinearLayoutManager(requireContext())

        if (adapter.itemCount == 0) {
            presenter.findAllCategories()
        }

        recycleView.adapter = adapter

        adapter.setOnItemClickListener { item, view ->
            val bundle = Bundle()
            bundle.putSerializable(JokeFragment.CATEGORY_KEY,(item as CategoryItem).category)
            findNavController().navigate(R.id.action_nav_home_to_nav_joke, bundle)
        }

    }

    fun showCategories(categories: List<Category>) {
        val categoriesItem = categories.map { CategoryItem(it) }
        adapter.addAll(categoriesItem)
    }

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}