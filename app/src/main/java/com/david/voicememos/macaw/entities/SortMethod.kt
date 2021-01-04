package com.david.voicememos.macaw.entities

import com.david.voicememos.macaw.R

enum class SortMethod(val userString: String,val drawable: Int) {
    DURATION("Duration", R.drawable.ic_baseline_access_time_24),
    DATE("Date", R.drawable.ic_baseline_calendar_today_24)
}