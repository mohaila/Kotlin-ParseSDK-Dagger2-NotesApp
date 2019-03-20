package ca.qc.mtl.mohaila.parsenotesapp.di.editnote

import ca.qc.mtl.mohaila.parsenotesapp.ui.editnote.EditNoteActivity
import ca.qc.mtl.mohaila.parsenotesapp.ui.editnote.EditNoteContract
import ca.qc.mtl.mohaila.parsenotesapp.ui.editnote.EditNotePresenter
import dagger.Module
import dagger.Provides

@Module
class EditNoteModule(private val view: EditNoteActivity) {

    @Provides
    fun providesView() : EditNoteContract.View = view

    @Provides
    fun providesPresenter() : EditNoteContract.Presenter = EditNotePresenter(view)
}