package ca.qc.mtl.mohaila.parsenotesapp.service.user

import ca.qc.mtl.mohaila.parsenotesapp.R
import ca.qc.mtl.mohaila.parsenotesapp.model.user.User
import com.parse.ParseUser
import javax.inject.Inject

class UserService @Inject constructor() {

    fun createUser(
        user: User,
        onSuccess: (Int) -> Unit,
        onFailure: (Int) -> Unit) {
        val parseUser = ParseUser()
        parseUser.username = user.name
        parseUser.email = user.email
        parseUser.setPassword(user.password)

        parseUser.signUpInBackground {
            if (it == null) {
                onSuccess(R.string.register_success)
            } else {
                onFailure(R.string.register_failure)
            }
        }
    }

    fun loginUser(
        user: User,
        onSuccess: (Int) -> Unit,
        onFailure: (Int) -> Unit
    ) {
        ParseUser.logInInBackground(user.name, user.password) { u, e ->
            if (u != null) {
                onSuccess(R.string.login_success)
            } else {
                onFailure(R.string.login_failure)
            }
        }
    }


    companion object {
        fun isUserConnected(): Boolean {
            if (ParseUser.getCurrentUser() != null)
                return true
            return false
        }
    }

}