package com.david.voicememos.macaw.entities

import android.media.MediaMetadataRetriever
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@Parcelize
data class Recording(
    val duration: String,
    val readableDate: String,
    val readableDayTime: String,
    val date: Date,
    val path: String
) : Parcelable


fun convertFilesToRecordings(file: List<File>): List<Recording> {

    val mmr = MediaMetadataRetriever()
    val recordingList = file.map {
        val calendar = Calendar.getInstance()
        calendar.time = Date(it.lastModified())

        mmr.setDataSource(it.path)
        val duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION) ?: "0"

        val timeDuration = convertDurationToString(duration.toInt())

        return@map Recording(
            readableDate = "${calendar.get(Calendar.DAY_OF_MONTH)} ${
                SimpleDateFormat(
                    "MMM",
                    Locale.getDefault()
                ).format(calendar.time)
            }",
            readableDayTime = "${
                SimpleDateFormat(
                    "EEEE",
                    Locale.getDefault()
                ).format(calendar.time)
            } at " + String.format(
                "%02d:%02d",
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE)
            ),
            duration = timeDuration,
            path = it.path,
            date = Date(it.lastModified())
        )
    }.toMutableList()

    mmr.release()
    return recordingList
}

fun convertDurationToString(duration: Int): String = String.format(
    "%02d:%02d",
    TimeUnit.MILLISECONDS.toMinutes(duration.toLong()),
    TimeUnit.MILLISECONDS.toSeconds(duration.toLong())
)

fun generateRecordingName(path: String?): String {
    return "${path}/Macaw-${
        SimpleDateFormat("ddMMyyyy-HHmmss", Locale.getDefault()).format(
            Calendar.getInstance().time
        )
    }.m4a"
}