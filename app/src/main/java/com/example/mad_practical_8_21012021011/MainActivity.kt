package com.example.mad_practical_8_21012021011

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun setAlarm(miliTime:Long , action:String){
        val intentalarm = Intent(applicationContext , AlarmService::class.java)
        intentalarm.putExtra(AlarmBroadcastReceiver.ALARMKEY, action)
        val pendingintent = PendingIntent.getBroadcast(applicationContext, 4245 , intentalarm , PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        val manager = getSystemService(ALARM_SERVICE) as AlarmManager
        if (action==AlarmBroadcastReceiver.ALARMSTART){
            manager.setExact(AlarmManager.RTC_WAKEUP , miliTime , pendingintent)
        }
        else if (action==AlarmBroadcastReceiver.ALARMSTOP){
            manager.cancel(pendingintent)
            sendBroadcast(intentalarm)
        }

    }
}