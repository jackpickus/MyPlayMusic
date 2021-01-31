package com.jackpickus.myplaymusic.models

import java.util.*

data class Album(val mId: UUID = UUID.randomUUID(), val title: String, val artist: String, val artwork: Int)