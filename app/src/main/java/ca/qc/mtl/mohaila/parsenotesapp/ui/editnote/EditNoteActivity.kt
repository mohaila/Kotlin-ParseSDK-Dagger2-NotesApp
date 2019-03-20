package ca.qc.mtl.mohaila.parsenotesapp.ui.editnote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import ca.qc.mtl.mohaila.parsenotesapp.R
import ca.qc.mtl.mohaila.parsenotesapp.di.editnote.EditNoteComponent
import ca.qc.mtl.mohaila.parsenotesapp.model.note.Note
import ca.qc.mtl.mohaila.parsenotesapp.ui.noteslist.NotesListActivity
import kotlinx.android.synthetic.main.activity_editnote.*
import javax.inject.Inject

class EditNoteActivity : AppCompatActivity(), EditNoteContract.View {

    private var action = -1
    private lateinit var note: Note
    @Inject
    lateinit var presenter: EditNoteContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_editnote)

        val component = EditNoteComponent.create(this)
        component.inject(this)

        action = intent.getIntExtra(NotesListActivity.ACTION, NotesListActivity.ACTION_ADD_NOTE)
        when (action) {
            NotesListActivity.ACTION_ADD_NOTE -> title = getString(R.string.add_note)
            NotesListActivity.ACTION_EDIT_NOTE -> {
                note = intent.getSerializableExtra(NotesListActivity.NOTE_EXTRA) as Note
                title = getString(R.string.edit_note)
                edit_title.setText(note.title)
                edit_desc.setText(note.description)
                urgent_switch.isChecked = note.urgent
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.editnote_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.save_note -> {
                when (action) {
                    NotesListActivity.ACTION_ADD_NOTE -> presenter.saveNote()
                    NotesListActivity.ACTION_EDIT_NOTE -> presenter.updateNote()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override val id: String?
        get() = note.id

    override val noteTitle: String
        get() = edit_title.text.toString()

    override val noteDescription: String
        get() = edit_desc.text.toString()

    override val noteUrgent: Boolean
        get() = urgent_switch.isChecked

    override fun setNoteTitleError(id: Int) {
        edit_title.error = getString(id)
    }

    override fun setNoteDescriptionError(id: Int) {
        edit_desc.error = getString(id)
    }


    override fun showMessage(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun showMessage(id: Int) = Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
}