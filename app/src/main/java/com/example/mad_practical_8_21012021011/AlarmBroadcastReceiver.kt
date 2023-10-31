package com.example.mad_practical_8_21012021011

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmBroadcastReceiver : BroadcastReceiver() {

    companion object{
        val ALARM_KEY = "key"
        val ALARM_START = "start"
        val ALARM_STOP = "stop"
    }

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        val DATA = intent.getStringExtra(ALARM_KEY)
        val intentservice = Intent(context,AlarmService::class.java)
        if(DATA == ALARM_START){
            context.startService(intentservice)
        }
        else{
            context.startService(intentservice)
        }
    }


}