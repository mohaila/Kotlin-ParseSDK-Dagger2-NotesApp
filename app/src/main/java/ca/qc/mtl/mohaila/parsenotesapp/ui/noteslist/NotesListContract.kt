package ca.qc.mtl.mohaila.parsenotesapp.ui.noteslist

import ca.qc.mtl.mohaila.parsenotesapp.model.note.Note

interface NotesListContract {
    interface View {
        fun setNotes(notes: ArrayList<Note>)
        fun showMessage(message: String)
        fun showMessage(id: Int)
        fun addNote(note: Note)
        fun updateNote(note: Note)
        fun deleteNote(note: Note)
    }

    interface Presenter {
        fun getNotes()
        fun updateNote(note: Note)
        fun deleteNote(note: Note)
    }
}