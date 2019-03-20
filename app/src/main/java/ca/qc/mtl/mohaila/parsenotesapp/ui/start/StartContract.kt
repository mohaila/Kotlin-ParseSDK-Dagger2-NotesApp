package ca.qc.mtl.mohaila.parsenotesapp.ui.start

interface StartContract {
    interface View {
        val username: String
        val password: String
        val email: String
        fun setUsernameError(id: Int)
        fun setPasswordError(id: Int)
        fun setEmailError(id: Int)
        fun hideEditEmail(hide: Int)
        fun startNotesListActivity()
        fun setActionButtonText(id: Int)
        fun setActionText(id: Int)
        fun showMessage(id: Int)
    }

    interface Presenter {
        var action: Int
        fun onButtonClick()
        fun onTextClick()
    }

    companion object {
        const val LOGIN = 0
        const val REGISTER = 1
    }
}