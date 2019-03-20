package ca.qc.mtl.mohaila.parsenotesapp.ui.noteslist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import ca.qc.mtl.mohaila.parsenotesapp.R
import ca.qc.mtl.mohaila.parsenotesapp.model.note.NoteDiffCallback
import ca.qc.mtl.mohaila.parsenotesapp.ui.editnote.EditNoteActivity
import kotlinx.android.synthetic.main.activity_noteslist.*

class NotesListActivity : AppCompatActivity() {

    private lateinit var adapter: NotesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_noteslist)

        recyclerview.setHasFixedSize(true)

        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = NotesListAdapter(this, NoteDiffCallback())
        recyclerview.adapter = adapter
        recyclerview.setHasFixedSize(true)

        fab.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            intent.putExtra(ACTION, ACTION_ADD_NOTE)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.noteslist_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.logout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ACTION = "action"
        const val NOTE_EXTRA = "note"
        const val ACTION_ADD_NOTE = 0
        const val ACTION_EDIT_NOTE = 1
    }
}