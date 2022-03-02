package br.com.digao1297.jokerapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.digao1297.jokerapp.R
import br.com.digao1297.jokerapp.data.JokeDayRemoteDataSource
import br.com.digao1297.jokerapp.model.Joke
import br.com.digao1297.jokerapp.presentation.JokeDayPresenter
import com.squareup.picasso.Picasso

class JokeDayFragment : Fragment() {

    private lateinit var jokeDayText: TextView
    private lateinit var jokeDayImage: ImageView
    private  lateinit var jokeDayProgress: ProgressBar
    private lateinit var presenter: JokeDayPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = JokeDayPresenter(this, JokeDayRemoteDataSource())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jokeDayProgress = view.findViewById(R.id.joke_day_progress_bar)
        jokeDayText = view.findViewById(R.id.joke_day_text)
        jokeDayImage = view.findViewById(R.id.joke_day_image)

        presenter.findTodayJoke()


    }

    fun showJoke(joke: Joke) {
        jokeDayText.text = joke.value
        Picasso.get().load(joke.iconUrl).into(jokeDayImage);
    }

    fun showProgressBar() {
        jokeDayProgress.visibility = View.VISIBLE
        jokeDayText.text = null
    }

    fun hideProgressBar() {
        jokeDayProgress.visibility = View.INVISIBLE
    }

    fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}