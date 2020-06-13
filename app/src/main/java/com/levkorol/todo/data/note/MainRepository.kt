package com.levkorol.todo.data.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.levkorol.todo.model.Folder
import com.levkorol.todo.model.Folder.Background.PURPLE
import com.levkorol.todo.model.Note
import com.levkorol.todo.model.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


object MainRepository {

    private lateinit var noteDao: NoteDao
    private lateinit var folderDao: FolderDao
    private lateinit var scheduleDao: ScheduleDao

    private var resultFolders: MutableLiveData<List<Folder>> = MutableLiveData()
    private var schedules: MutableLiveData<List<Schedule>> = MutableLiveData()
    var notes: List<Note>? = null
    var folders: List<Folder>? = null
    var schedule: List<Schedule>? = null


    fun initialize(database: NoteDatabase) {
        noteDao = database.noteDao()
        folderDao = database.folderDao()
        scheduleDao = database.scheduleDao()

        scheduleDao.getAll().observeForever { schedule ->
            this.schedule = schedule
            schedules.value = schedule
        }

        noteDao.getAll().observeForever { notes ->
            this.notes = notes
            process()
        }

        folderDao.getAll().observeForever { folders ->
            this.folders = folders
            process()
        }
    }

    //Schedule
    fun getSchedules(): LiveData<List<Schedule>> = scheduleDao.getAll()

    fun getSchedule(): LiveData<Schedule> = scheduleDao.getId(-1)

    fun addSchedule(schedule: Schedule) {
        GlobalScope.launch(Dispatchers.IO) {
            scheduleDao.insert(schedule)
        }
    }

    fun deleteSchedule(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            scheduleDao.deleteById(id)
        }
    }

    fun updateSchedule(schedule: Schedule){
        GlobalScope.launch(Dispatchers.IO) {
            scheduleDao.update(schedule)
        }
    }

    //Notes
    @Deprecated("use getNotes() instead")
    fun getDeprecatedNotes(): LiveData<List<Note>> = noteDao.getAll()

    fun getNotes(): LiveData<List<Note>> = noteDao.getAll()

    fun getNote(): LiveData<Note> = noteDao.getNoteId(-1)

    fun addNote(note: Note) {
        GlobalScope.launch(Dispatchers.IO) {
            noteDao.insert(note)
        }
    }

    fun deleteById(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            noteDao.deleteById(id)
        }
    }

    fun update(note: Note) {
        GlobalScope.launch(Dispatchers.IO) {
            noteDao.update(note)
        }
    }

    // Folders
    fun getFolders(): LiveData<List<Folder>> = resultFolders

    fun getFolder(): LiveData<Folder> = folderDao.getFolderId(-1)

    fun addFolder(folder: Folder) {
        GlobalScope.launch(Dispatchers.IO) {
            folderDao.insert(folder)
        }
    }

    fun deleteFolderById(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            folderDao.deleteById(id)
        }
    }

    fun update(folder: Folder) {
        GlobalScope.launch(Dispatchers.IO) {
            folderDao.update(folder)
        }
    }

    private fun process() {
        if (folders == null || notes == null) {
            return
        }
        val rootFolder = Folder(
            -1, "Kornevaya papka",
            "Kornevaya papka opisaniye", Folder.Background.PINK,
            0, 0
        )
        rootFolder.notes = notes!!.filter { it.parentFolderId == -1L }
        rootFolder.folders = folders!!.filter { it.parentFolderId == -1L }
        for (element in folders!!) {
            val childNotes = notes!!.filter { it.parentFolderId == element.id }
            element.notes = childNotes
            val childFolders = folders!!.filter { it.parentFolderId == element.id }
            element.folders = childFolders
        }
        folders = folders!!.union(listOf(rootFolder)).toList()
        resultFolders.value = folders
    }
}

