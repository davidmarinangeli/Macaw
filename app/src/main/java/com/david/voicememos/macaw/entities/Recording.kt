package com.david.voicememos.macaw.entities

import android.media.MediaMetadataRetriever
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit


data class Recording(
    val duration: String,
    val date: String,
    val dayAndTime: String
)


fun convertFilesToRecordings(file: List<File>): List<Recording> {

    val mmr = MediaMetadataRetriever()
    val recordingList = file.map {
        val calendar = Calendar.getInstance()
        calendar.time = Date(it.lastModified())

        mmr.setDataSource(it.path)
        val duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION).toLong()

        val timeDuration = String.format("%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(duration),
            TimeUnit.MILLISECONDS.toSeconds(duration))

        return@map Recording(
            date = "${calendar.get(Calendar.DAY_OF_MONTH)} ${
                SimpleDateFormat(
                    "MMM",
                    Locale.getDefault()
                ).format(calendar.time)
            }",
            dayAndTime = "${
                SimpleDateFormat(
                    "EEEE",
                    Locale.getDefault()
                ).format(calendar.time)
            } at " +
                    "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}",
            duration = timeDuration,
        )
    }

    mmr.release()
    return recordingList
}