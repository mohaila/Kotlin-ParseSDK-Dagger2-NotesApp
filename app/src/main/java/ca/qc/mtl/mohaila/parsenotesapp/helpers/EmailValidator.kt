package ca.qc.mtl.mohaila.parsenotesapp.helpers

import java.util.regex.Pattern

object EmailValidator {
    fun validate(email: String) : Boolean {
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private val pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
}