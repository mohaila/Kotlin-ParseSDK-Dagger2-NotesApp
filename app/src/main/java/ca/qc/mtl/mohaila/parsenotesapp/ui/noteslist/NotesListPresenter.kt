package ca.qc.mtl.mohaila.parsenotesapp.ui.noteslist

import ca.qc.mtl.mohaila.parsenotesapp.di.noteslist.NotesListComponent
import ca.qc.mtl.mohaila.parsenotesapp.model.note.Note
import ca.qc.mtl.mohaila.parsenotesapp.service.note.NoteService
import ca.qc.mtl.mohaila.parsenotesapp.service.user.UserService
import javax.inject.Inject

class NotesListPresenter (
    private val view: NotesListContract.View
) : NotesListContract.Presenter {

    @Inject
    lateinit var service: NoteService
    @Inject
    lateinit var userService: UserService

    init {
        NotesListComponent.INSTANCE.inject(this)
    }

    override fun getNotes() =  service.getNotes(onGetNotesSuccess, onFailure)

    val onGetNotesSuccess = { notes: ArrayList<Note> ->
        view.setNotes(notes)
    }

    val onFailure = {id: Int ->
        view.showMessage(id)
    }

    override fun updateNote(note: Note) = service.updateNote(note, onUpdateSuccess, onFailure)

    val onUpdateSuccess = {note: Note ->
        view.updateNote(note)
    }

    override fun deleteNote(note: Note) = service.deleteNote(note, onDeleteNoteSuccess, onFailure)

    val onDeleteNoteSuccess = {note: Note->
        view.deleteNote(note)
    }

}