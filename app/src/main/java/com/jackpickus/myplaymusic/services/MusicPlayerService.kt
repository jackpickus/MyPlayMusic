package com.jackpickus.myplaymusic.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.jackpickus.myplaymusic.R
import com.jackpickus.myplaymusic.activities.MusicListActivity
import com.jackpickus.myplaymusic.fragments.MusicListFragment
import com.jackpickus.myplaymusic.models.Music
import java.util.*

private const val MUSIC_PLAYING_NOTIFICATION_ID: Int = 1
private const val TAG = "MusicPlayerService"
private const val ARG_SONG_ID = "song_id"
private const val CHANNEL_DEFAULT_IMPORTANCE: String = "PRIORITY_LOW"

class MusicPlayerService : Service() {
    private val localIBinder = LocalBinder()
    private var mMediaPlayer: MediaPlayer? = null
    private lateinit var id: UUID

    inner class LocalBinder : Binder() {
        fun getService(): MusicPlayerService = this@MusicPlayerService
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            Log.d(TAG, "onCreate() called with startServiceWithNotification()")
            startServiceWithNotification()
        } else {
            Log.d(TAG, "onCreate() called with Notification() ie SDK < 26")
            startForeground(9999, Notification())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer?.stop()
        stopForeground(true)
        stopSelf()
        Log.d(TAG, "onDestroy() called and mMediaPlayer has stopped")

    }

    override fun onBind(intent: Intent?): IBinder {
        return localIBinder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startServiceWithNotification() {

        val name = getString(R.string.channel_name)
        val descriptionText = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_DEFAULT_IMPORTANCE, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        val pendingIntent: PendingIntent =
                Intent(this, MusicListActivity::class.java).let { notificationIntent ->
                    PendingIntent.getActivity(this, 0, notificationIntent, 0)
                }

        val notification: Notification = Notification.Builder(this, CHANNEL_DEFAULT_IMPORTANCE)
                .setContentTitle(getText(R.string.notification_title))
                .setContentText(getText(R.string.notification_message))
                .setContentIntent(pendingIntent)
                .setTicker(getText(R.string.ticker_text))
                .build()

        startForeground(MUSIC_PLAYING_NOTIFICATION_ID, notification)

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val musicId = intent?.getSerializableExtra(ARG_SONG_ID) as UUID?

        val currentSong: Music? = findSong(musicId)

        if (musicId != null) {
            id = musicId
        }

        initMediaPlayer(currentSong)

        // If we get killed, after returning from here, restart
        return START_STICKY
    }

    private fun findSong(id: UUID?): Music? {
        for (song in MusicListFragment.newMusics) {
            if (song.id == id) {
                return song
            }
        }
        return null
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initMediaPlayer(song: Music?) {

        val songUri: Uri = Uri.parse(song?.data)
        mMediaPlayer?.reset()
        mMediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                    AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            )
            setDataSource(applicationContext, songUri)
            prepare()
            isLooping = false
            start()
        }


    }

    fun play() {
        if (mMediaPlayer != null) {
            if (!mMediaPlayer!!.isPlaying) {
                mMediaPlayer?.start()
            }
        }
    }

    fun pause() {
        mMediaPlayer?.pause()
    }

    fun isMediaPlaying(): Boolean {
        if (mMediaPlayer != null) {
            if (mMediaPlayer!!.isPlaying) {
                return true
            }
        }
        return false
    }

    fun getId(): UUID {
        return id
    }

}