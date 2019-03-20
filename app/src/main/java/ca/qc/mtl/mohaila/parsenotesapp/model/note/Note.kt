package ca.qc.mtl.mohaila.parsenotesapp.model.note

import android.support.v7.util.DiffUtil
import java.io.Serializable
import javax.inject.Inject


data class Note(val urgent: Boolean, val title: String, val description: String, val id: String? = null) : Serializable

class NoteDiffCallback @Inject constructor() : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(note1: Note, note2: Note): Boolean  = note1.id == note2.id

    override fun areContentsTheSame(note1: Note, note2: Note): Boolean  = note1.equals(note2)
}