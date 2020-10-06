package com.david.voicememos.macaw

import com.david.voicememos.macaw.entities.Recording

interface Repo {
    suspend fun getAllPolls(): List<Recording>
}