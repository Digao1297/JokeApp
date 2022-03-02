package br.com.digao1297.jokerapp.presentation

import android.graphics.Color
import br.com.digao1297.jokerapp.data.DefaultCallback
import br.com.digao1297.jokerapp.data.CategoryRemoteDataSource
import br.com.digao1297.jokerapp.model.Category
import br.com.digao1297.jokerapp.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource
) : DefaultCallback<List<String>> {

    fun findAllCategories() {
        view.showProgressBar()
        dataSource.findAllCategories(this)
    }

    override fun onSuccess(response: List<String>) {
        val start = 40
        val end = 190
        val diff = (end - start)/response.size

        val categories = response.mapIndexed {index,value ->
            val hsv = floatArrayOf(
                start + (diff * index).toFloat(),
                100.0f,
                100.0f
            )
            Category(value, Color.HSVToColor(hsv).toLong())
        }
        view.showCategories(categories)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }


}