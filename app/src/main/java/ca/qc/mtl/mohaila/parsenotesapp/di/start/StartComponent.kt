package ca.qc.mtl.mohaila.parsenotesapp.di.start

import ca.qc.mtl.mohaila.parsenotesapp.ui.start.StartActivity
import ca.qc.mtl.mohaila.parsenotesapp.ui.start.StartPresenter
import dagger.Component

@Component(modules = [StartModule::class])
interface StartComponent {
    fun inject(activity: StartActivity)

    fun inject(presenter: StartPresenter)

    companion object {
        lateinit var INSTANCE: StartComponent

        fun create(activity: StartActivity): StartComponent {
            INSTANCE = DaggerStartComponent
                .builder()
                .startModule(StartModule(activity))
                .build()

            return INSTANCE
        }
    }
}