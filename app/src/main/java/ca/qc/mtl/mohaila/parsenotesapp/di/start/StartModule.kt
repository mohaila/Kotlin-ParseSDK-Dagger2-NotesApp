package ca.qc.mtl.mohaila.parsenotesapp.di.start

import ca.qc.mtl.mohaila.parsenotesapp.ui.start.StartActivity
import ca.qc.mtl.mohaila.parsenotesapp.ui.start.StartContract
import ca.qc.mtl.mohaila.parsenotesapp.ui.start.StartPresenter
import dagger.Module
import dagger.Provides

@Module
class StartModule(private val activity: StartActivity) {

    @Provides
    fun provideView() : StartContract.View = activity

    @Provides
    fun ProvidesPresenter() : StartContract.Presenter = StartPresenter(activity)
}