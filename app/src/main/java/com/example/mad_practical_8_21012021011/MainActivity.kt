package com.example.mad_practical_8_21012021011

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addAlarm : MaterialButton = findViewById(R.id.createAlarm)
        val card : MaterialCardView = findViewById(R.id.mcv1)

        card.visibility = View.GONE

        addAlarm.setOnClickListener {
            TimePickerDialog(this, {tp, hour, minute -> setAlarmTime(hour, minute) }, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), false).show()
            card.visibility = View.VISIBLE
        }

        val cancelAlarm : MaterialButton = findViewById(R.id.createAlarmmcv1)
        cancelAlarm.setOnClickListener {
            stop()
            card.visibility = View.GONE
        }
    }

    private fun setAlarmTime(hour : Int, minute : Int){
        val alarmtime = Calendar.getInstance()
        val year = alarmtime.get(Calendar.YEAR)
        val month = alarmtime.get(Calendar.MONTH)
        val date = alarmtime.get(Calendar.DATE)
        alarmtime.set(year, month, date, hour, minute, 0)
        val textAlarmTime : TextClock = findViewById(R.id.textClock)
        textAlarmTime.text = SimpleDateFormat("hh:mm:ss a").format(alarmtime.time)
        setAlarm(alarmtime.timeInMillis, AlarmBroadcastReceiver.ALARM_START)
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_LONG).show()
    }

    fun stop() {
        setAlarm(-1, AlarmBroadcastReceiver.ALARM_STOP)
    }

    fun setAlarm(militime : Long, action : String) {
        val intentalarm = Intent(applicationContext, AlarmBroadcastReceiver::class.java)
        intentalarm.putExtra(AlarmBroadcastReceiver.ALARM_KEY, action)
        val pendingintent = PendingIntent.getBroadcast(applicationContext, 4356, intentalarm, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val alarmmanager = getSystemService(ALARM_SERVICE) as AlarmManager

        if (action == AlarmBroadcastReceiver.ALARM_START) {
            alarmmanager.setExact(AlarmManager.RTC_WAKEUP, militime, pendingintent)
        }
        else if (action == AlarmBroadcastReceiver.ALARM_STOP) {
            alarmmanager.cancel(pendingintent)
            sendBroadcast(intentalarm)
        }
    }
}