package ca.qc.mtl.mohaila.parsenotesapp.ui.start

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import ca.qc.mtl.mohaila.parsenotesapp.R
import ca.qc.mtl.mohaila.parsenotesapp.di.start.StartComponent
import ca.qc.mtl.mohaila.parsenotesapp.service.user.UserService
import ca.qc.mtl.mohaila.parsenotesapp.ui.app.NotesApp
import ca.qc.mtl.mohaila.parsenotesapp.ui.noteslist.NotesListActivity
import kotlinx.android.synthetic.main.activity_start.*
import javax.inject.Inject

class StartActivity : AppCompatActivity(), StartContract.View {

    @Inject
    lateinit var presenter: StartContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (UserService.isUserConnected()) {
            val intent = Intent(this, NotesListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
            return
        }

        setContentView(R.layout.activity_start)

        val component = StartComponent.create(this)
        component.inject(this)

        button_action.setOnClickListener {
            presenter.onButtonClick()
        }

        text_action.setOnClickListener {
            presenter.onTextClick()
        }
    }


    override val username: String
        get() = edit_username.text.toString()

    override val password: String
        get() = edit_password.text.toString()

    override val email: String
        get() = edit_email.text.toString()

    override fun setUsernameError(id: Int) {
        edit_username.error = getString(id)
    }

    override fun setPasswordError(id: Int) {
        edit_password.error = getString(id)
    }

    override fun setEmailError(id: Int) {
        edit_email.error = getString(id)
    }

    override fun hideEditEmail(hide: Int) {
        edit_email.visibility = hide
    }

    override fun startNotesListActivity() {
        val intent = Intent(this, NotesListActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun setActionButtonText(id: Int) {
        button_action.text = getString(id)
    }

    override fun setActionText(id: Int) {
        text_action.text = getString(id)
    }

    override fun showMessage(id: Int) = Toast.makeText(this, id, Toast.LENGTH_SHORT).show()

}
