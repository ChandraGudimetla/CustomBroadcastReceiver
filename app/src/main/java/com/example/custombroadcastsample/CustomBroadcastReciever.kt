package com.example.custombroadcastsample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomBroadcastReciever : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val intentAction = intent?.action
        if (intentAction != null){
            var toastMessage:String = "Unknown intent action"
            when(intentAction) {
                Intent.ACTION_AIRPLANE_MODE_CHANGED -> toastMessage =
                    context.getString(R.string.airplane_mode_change)
                Intent.ACTION_POWER_CONNECTED -> toastMessage =
                    context.getString(R.string.power_connected)
                Intent.ACTION_POWER_DISCONNECTED -> toastMessage =
                    context.getString(R.string.power_disconnected)
                ACTION_CUSTOM_BROADCAST -> toastMessage =
                    context.getString(R.string.custom_broadcast_toast)
            }
            Toast.makeText(context,toastMessage,Toast.LENGTH_LONG).show()
        }
    }

    companion object{
        private const val ACTION_CUSTOM_BROADCAST : String = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"
    }
}