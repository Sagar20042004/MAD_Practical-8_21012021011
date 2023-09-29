package com.example.mad_practical_8_21012021011

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmBroadcastReceiver : BroadcastReceiver() {

    companion object{
        val ALARMKEY = "key"
        val ALARMSTART = "start"
        val ALARMSTOP = "stop"

    }
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        val data = intent.getStringExtra(ALARMKEY)

        val intentservice = Intent(context , AlarmService::class.java)

        if (data== ALARMSTART){
            context.startService(intentservice)
        }
        else{
            context.stopService(intentservice)
        }

    }
}