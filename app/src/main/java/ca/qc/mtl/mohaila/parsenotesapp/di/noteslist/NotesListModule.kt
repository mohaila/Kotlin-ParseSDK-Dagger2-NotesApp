package ca.qc.mtl.mohaila.parsenotesapp.di.noteslist

import ca.qc.mtl.mohaila.parsenotesapp.ui.noteslist.NotesListAdapter
import ca.qc.mtl.mohaila.parsenotesapp.ui.noteslist.NotesListContract
import ca.qc.mtl.mohaila.parsenotesapp.ui.noteslist.NotesListPresenter
import dagger.Module
import dagger.Provides

@Module
class NotesListModule(private val adapter: NotesListAdapter) {

    @Provides
    fun provideView() : NotesListContract.View = adapter

    @Provides
    fun providePresenter() : NotesListContract.Presenter = NotesListPresenter(adapter)
}