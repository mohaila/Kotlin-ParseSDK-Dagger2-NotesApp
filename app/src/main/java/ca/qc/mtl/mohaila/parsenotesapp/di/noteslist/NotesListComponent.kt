package ca.qc.mtl.mohaila.parsenotesapp.di.noteslist

import ca.qc.mtl.mohaila.parsenotesapp.ui.noteslist.NotesListAdapter
import ca.qc.mtl.mohaila.parsenotesapp.ui.noteslist.NotesListPresenter
import dagger.Component

@Component(modules = [NotesListModule::class])
interface NotesListComponent {

    fun inject(adapter: NotesListAdapter)

    fun inject(presenter: NotesListPresenter)


    companion object {
        lateinit var INSTANCE: NotesListComponent
        fun create(adapter: NotesListAdapter) : NotesListComponent {
            INSTANCE = DaggerNotesListComponent
                .builder()
                .notesListModule(NotesListModule(adapter))
                .build()

            return INSTANCE
        }
    }
}