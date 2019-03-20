package ca.qc.mtl.mohaila.parsenotesapp.ui.editnote

import ca.qc.mtl.mohaila.parsenotesapp.R
import ca.qc.mtl.mohaila.parsenotesapp.model.note.Note
import ca.qc.mtl.mohaila.parsenotesapp.service.note.NoteService
import javax.inject.Inject

class EditNotePresenter @Inject constructor(private val view: EditNoteContract.View) : EditNoteContract.Presenter {
    
    val service: NoteService = NoteService()
    
    override fun saveNote() {
        validateNote()

        val note = Note(view.noteUrgent, view.noteTitle, view.noteDescription)
        service.createNote(note, onSaveSucess, onFailure)

    }

    override fun updateNote() {
        validateNote()

        val note = Note(view.noteUrgent, view.noteTitle, view.noteDescription, view.id)
        service.updateNote(note, onUpdateSuccess, onFailure)
    }

    private fun validateNote() {
        if (view.noteTitle.trim().isEmpty()) {
            view.setNoteTitleError(R.string.title_empty_error)
            return
        }

        if(view.noteDescription.trim().isEmpty()) {
            view.setNoteDescriptionError(R.string.desc_empty_error)
            return
        }
    }
    
    val onFailure = {id: Int ->
        view.showMessage(id)
    }
    
    val onSaveSucess = {_: Note ->
        view.showMessage(R.string.note_created)   
    }
    
    val onUpdateSuccess = {_: Note ->
        view.showMessage(R.string.note_updated)
    }
}