package ca.qc.mtl.mohaila.parsenotesapp.di.editnote

import ca.qc.mtl.mohaila.parsenotesapp.ui.editnote.EditNoteActivity
import dagger.Component

@Component(modules = [EditNoteModule::class])
interface EditNoteComponent {

    fun inject(view: EditNoteActivity)

    companion object {
        lateinit var INSTANCE: EditNoteComponent

        fun create(activity: EditNoteActivity): EditNoteComponent {
            INSTANCE = DaggerEditNoteComponent
                .builder()
                .editNoteModule(EditNoteModule(activity))
                .build()

            return INSTANCE
        }
    }
}