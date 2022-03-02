package br.com.digao1297.jokerapp.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import br.com.digao1297.jokerapp.R
import br.com.digao1297.jokerapp.data.JokeRemoteDataSource
import br.com.digao1297.jokerapp.model.Category
import br.com.digao1297.jokerapp.model.Joke
import br.com.digao1297.jokerapp.presentation.HomePresenter
import br.com.digao1297.jokerapp.presentation.JokePresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieAdapter

class JokeFragment : Fragment() {

    private lateinit var presenter: JokePresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var jokeText: TextView
    private lateinit var jokeImage: ImageView
    private lateinit var jokeFloating: FloatingActionButton

    companion object {
        const val CATEGORY_KEY = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = JokePresenter(this, JokeRemoteDataSource())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryArgs = arguments?.getSerializable(CATEGORY_KEY) as Category

        categoryArgs.let { category ->
            progressBar = view.findViewById(R.id.joke_progress_bar)
            jokeText = view.findViewById(R.id.joke_text)
            jokeImage = view.findViewById(R.id.joke_image)
            jokeFloating = view.findViewById(R.id.joke_floating)

            presenter.findRandomJoke(category)

            activity?.findViewById<Toolbar>(R.id.toolbar)?.title =
                category.name.replaceFirstChar(Char::titlecase)
//            jokeText.setTextColor(category.bgColor.toInt())

            jokeFloating.setOnClickListener {
                presenter.findRandomJoke(category)
            }
        }


    }

    fun showJoke(joke: Joke) {
        jokeText.text = joke.value
        Picasso.get().load(joke.iconUrl).into(jokeImage);
    }

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        jokeFloating.isEnabled = false;
        jokeText.text = null
    }

    fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
        jokeFloating.isEnabled = true;
    }

    fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}