package ca.qc.mtl.mohaila.parsenotesapp.ui.noteslist

import android.content.Context
import android.content.Intent
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import ca.qc.mtl.mohaila.parsenotesapp.R
import ca.qc.mtl.mohaila.parsenotesapp.di.noteslist.NotesListComponent
import ca.qc.mtl.mohaila.parsenotesapp.model.note.Note
import ca.qc.mtl.mohaila.parsenotesapp.model.note.NoteDiffCallback
import ca.qc.mtl.mohaila.parsenotesapp.ui.editnote.EditNoteActivity
import javax.inject.Inject

class NotesListAdapter(private val context: Context, noteDiff: NoteDiffCallback) :
    ListAdapter<Note, NotesListAdapter.ViewHolder>(noteDiff),
    NotesListContract.View {

    @Inject
    lateinit var presenter: NotesListContract.Presenter

    private var notes = ArrayList<Note>()

    init {
        val component = NotesListComponent.create(this)
        component.inject(this)
        presenter.getNotes()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_noteslist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTitle.text = notes[position].title
        holder.noteDesc.text = notes[position].description
        if (notes[position].urgent)
            holder.noteImage.setImageResource(R.drawable.red_ball)
        else
            holder.noteImage.setImageResource(R.drawable.green_ball)
    }

    override fun getItemCount(): Int = notes.size

    override fun setNotes(notes: ArrayList<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun addNote(note: Note) {
        notes.add(note)
        notifyItemChanged(notes.size - 1)
    }

    override fun updateNote(note: Note) {
        val position = notes.indexOfFirst {
            it.id == note.id
        }
        notes[position] = note
        notifyItemChanged(position)
    }

    override fun deleteNote(note: Note) {
        val position = notes.indexOfFirst {
            it.id == note.id
        }
        notes.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun showMessage(message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    override fun showMessage(id: Int) = Toast.makeText(context, id, Toast.LENGTH_SHORT).show()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardview = itemView.findViewById<CardView>(R.id.cardview)
        val noteImage = itemView.findViewById<ImageView>(R.id.note_urgent)
        val noteTitle = itemView.findViewById<TextView>(R.id.note_title)
        val noteDesc = itemView.findViewById<TextView>(R.id.note_desc)
        val editButton = itemView.findViewById<ImageButton>(R.id.edit_button)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.delete_button)

        init {
            cardview.setOnClickListener {
                val note = this@NotesListAdapter.notes[adapterPosition]
                val newNote = Note(!note.urgent, note.title, note.description, note.id)

                presenter.updateNote(newNote)
            }

            editButton.setOnClickListener {
                val intent = Intent(context, EditNoteActivity::class.java)
                intent.putExtra(NotesListActivity.ACTION, NotesListActivity.ACTION_EDIT_NOTE)
                intent.putExtra(NotesListActivity.NOTE_EXTRA, this@NotesListAdapter.notes[adapterPosition])
                context.startActivity(intent)
            }

            deleteButton.setOnClickListener {
                val note = this@NotesListAdapter.notes[adapterPosition]
                presenter.deleteNote(note)
            }
        }

    }
}