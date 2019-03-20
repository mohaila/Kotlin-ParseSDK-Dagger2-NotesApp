package ca.qc.mtl.mohaila.parsenotesapp.ui.start

import android.view.View.*
import android.widget.Toast
import ca.qc.mtl.mohaila.parsenotesapp.R
import ca.qc.mtl.mohaila.parsenotesapp.di.start.StartComponent
import ca.qc.mtl.mohaila.parsenotesapp.model.user.User
import ca.qc.mtl.mohaila.parsenotesapp.helpers.EmailValidator
import ca.qc.mtl.mohaila.parsenotesapp.service.user.UserService
import ca.qc.mtl.mohaila.parsenotesapp.ui.app.NotesApp
import javax.inject.Inject

class StartPresenter @Inject constructor(val view: StartContract.View) : StartContract.Presenter {

    @Inject
    lateinit var userService: UserService

    override var action: Int = StartContract.REGISTER

    init {
        StartComponent.INSTANCE.inject(this)
    }

    override fun onButtonClick() {
        var email = ""
        if (action == StartContract.REGISTER) {
            email =  view.email
            if (email.trim().isEmpty()) {
                view.setEmailError(R.string.empty_email)
                return
            }

            if(!EmailValidator.validate(email)) {
                view.setEmailError(R.string.invalid_email)
                return
            }
        }

        val username = view.username
        if (username.trim().isEmpty()) {
            view.setUsernameError(R.string.empty_username)
            return
        }
        if(username.length < 8) {
            view.setUsernameError(R.string.short_username)
            return
        }

        val password = view.password
        if (password.trim().isEmpty()) {
            view.setPasswordError(R.string.empty_password)
            return
        }
        if(password.length < 8) {
            view.setPasswordError(R.string.short_password)
            return
        }

        val user = User(username, email, password)

        when (action) {
            StartContract.REGISTER -> userService.createUser(user, onRegisterSuccess, onRegisterFailure)
            StartContract.LOGIN -> userService.loginUser(user, onLoginSuccess, onLoginFailure)
        }
    }

    private val onRegisterSuccess = {id: Int ->
        view.showMessage(id)
        onTextClick()
    }

    private val onRegisterFailure = {id: Int ->
        view.showMessage(id)
    }

    private val onLoginSuccess = {id: Int ->
        view.showMessage(id)
        view.startNotesListActivity()
    }

    private val onLoginFailure = {id: Int ->
        view.showMessage(id)
    }

    override fun onTextClick() {
        when(action) {
            StartContract.REGISTER -> {
                action = StartContract.LOGIN
                view.hideEditEmail(GONE)
                view.setActionButtonText(R.string.login)
                view.setActionText(R.string.register)
            }
            StartContract.LOGIN -> {
                action = StartContract.REGISTER
                view.hideEditEmail(VISIBLE)
                view.setActionButtonText(R.string.register)
                view.setActionText(R.string.login)
            }
        }
    }
}