package ca.qc.mtl.mohaila.parsenotesapp.ui.editnote


interface EditNoteContract {

    interface View {
        val noteTitle: String
        val noteDescription: String
        val noteUrgent: Boolean
        val id: String?

        fun setNoteTitleError(id: Int)
        fun setNoteDescriptionError(id: Int)
        fun showMessage(message: String)
        fun showMessage(id: Int)
    }

    interface Presenter {
        fun saveNote()
        fun updateNote()
    }

}