package br.com.digao1297.jokerapp.view

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import br.com.digao1297.jokerapp.R
import br.com.digao1297.jokerapp.data.ApiKeyRemoteDataSource
import br.com.digao1297.jokerapp.presentation.DialogApiKeyPresenter
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class DialogApiKeyFragment(private val dataChanged: DataChanged) : DialogFragment() {
    private lateinit var progress: ProgressBar
    private lateinit var emailEditText: EditText
    private lateinit var button: Button
    private lateinit var presenter: DialogApiKeyPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = DialogApiKeyPresenter(this, get<ApiKeyRemoteDataSource>())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_api_key, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress = view.findViewById(R.id.dialog_api_key_progress)
        emailEditText = view.findViewById(R.id.dialog_api_key_email)
        button = view.findViewById(R.id.dialog_api_key_button)


        view.findViewById<Button>(R.id.dialog_api_key_button).setOnClickListener {

            if (emailEditText.text.isNotEmpty()) {
                presenter.getApiKey(emailEditText.text.toString())
            }
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }


    fun refreshHomeFragment(){
        dataChanged.onDataChanged()
    }

    fun showProgressBar() {
        progress.visibility = View.VISIBLE
        button.visibility = View.GONE
        closeKeyboard()
    }

    fun hideProgressBar() {
        progress.visibility = View.GONE
        button.visibility = View.VISIBLE

    }

    fun dismissDialog() {
        this.dismiss()
    }

    fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun closeKeyboard(){
        emailEditText.onEditorAction(EditorInfo.IME_ACTION_DONE)
    }

}