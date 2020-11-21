package com.david.voicememos.macaw.repository

import com.david.voicememos.macaw.entities.Recording
import com.david.voicememos.macaw.entities.convertFilesToRecordings
import java.io.File

class HomeRepository {

    fun getRecordings(directory: String): List<Recording> {
        val file = File(directory)
        return convertFilesToRecordings(file.listFiles()?.toList() ?: listOf())
    }

}