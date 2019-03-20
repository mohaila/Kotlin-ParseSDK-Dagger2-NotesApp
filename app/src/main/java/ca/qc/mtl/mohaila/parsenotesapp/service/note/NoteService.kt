package ca.qc.mtl.mohaila.parsenotesapp.service.note

import ca.qc.mtl.mohaila.parsenotesapp.R
import ca.qc.mtl.mohaila.parsenotesapp.model.note.Note
import ca.qc.mtl.mohaila.parsenotesapp.ui.app.NotesApp
import com.parse.ParseObject
import com.parse.ParseQuery
import javax.inject.Inject

class NoteService @Inject constructor() {

    fun createNote(
        note: Note,
        onSuccess: (Note) -> Unit,
        onFailure: (Int) -> Unit
    ) {
        val noteObject = ParseObject("Note")
        noteObject.put("urgent", note.urgent)
        noteObject.put("title", note.title)
        noteObject.put("description", note.description)

        noteObject.saveInBackground {
            if (it == null) {
                val newNote = Note(note.urgent, note.title, note.description, noteObject.objectId)
                onSuccess(newNote)
            } else {
                onFailure(R.string.save_note_error)
            }

        }
    }

    fun updateNote(
        note: Note,
        onSuccess: (Note) -> Unit,
        onFailure: (Int) -> Unit
    ) {
        val query = ParseQuery.getQuery<ParseObject>("Note")
        query.getInBackground(note.id) { entity, e ->
            if (e == null) {
                entity.put("urgent", note.urgent)
                entity.put("title", note.title)
                entity.put("description", note.description)

                entity.saveInBackground {
                    if (it == null)
                        onSuccess(note)
                    else {
                        onFailure(R.string.update_note_err)
                    }
                }
            } else {
                onFailure(R.string.update_note_err)
            }
        }

    }

    fun deleteNote(
        note: Note,
        onSuccess: (Note) -> Unit,
        onFailure: (Int) -> Unit
    ) {
        val query = ParseQuery.getQuery<ParseObject>("Note")
        query.getInBackground(note.id) { entity, e ->
            if (e == null) {
                entity.deleteInBackground {
                    if (it == null)
                        onSuccess(note)
                    else {
                        onFailure(R.string.delete_note_err)
                    }
                }
            } else {
                onFailure(R.string.delete_note_err)
            }

        }
    }

    fun getNotes(
        onSuccess: (ArrayList<Note>) -> Unit,
        onFailure: (Int) -> Unit
    ) {
        val query = ParseQuery.getQuery<ParseObject>("Note")
        query.findInBackground { entities, e ->
            if (e == null) {
                val notes = ArrayList<Note>()
                for (entity in entities) {
                    val urgent = entity["urgent"] as Boolean
                    val title = entity["title"] as String
                    val description = entity["description"] as String
                    val note = Note(urgent, title, description, entity.objectId)

                    notes.add(note)
                }
                onSuccess(notes)
            } else {
                onFailure(R.string.get_notes_err)
            }
        }
    }
}