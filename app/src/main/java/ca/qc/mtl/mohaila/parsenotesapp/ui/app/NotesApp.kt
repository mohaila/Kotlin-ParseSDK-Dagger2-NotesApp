package ca.qc.mtl.mohaila.parsenotesapp.ui.app

import android.app.Application
import com.parse.Parse

class NotesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(APP_ID)
                .clientKey(CLIENT_KEY)
                .server(SERVER)
                .build()
        )
    }

    companion object {
        private const val APP_ID = "vSl8B7MbbFqoP4PYzH4qniMUVVkx2CbYdJclIdA6"
        private const val CLIENT_KEY = "OTmIqssMFrve2mVByXEd8dpyDwWDvCIMI5vLfoC9"
        private const val SERVER = "https://parseapi.back4app.com"
    }
}