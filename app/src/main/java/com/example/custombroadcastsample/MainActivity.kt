package com.example.custombroadcastsample

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    val mReciever: CustomBroadcastReciever = CustomBroadcastReciever()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter()
        // we are adding system broadcast actions send by the system when the power is connected and disconnected
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        //Register the receiver using the activity context, passing in the IntentFilter

        this.registerReceiver(mReciever, filter)
        LocalBroadcastManager.getInstance(this).registerReceiver(mReciever,
            IntentFilter(ACTION_CUSTOM_BROADCAST)
        )

    }

    fun sendCustomBroadcast(view: View?){
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent)
    }

    companion object{
        private val ACTION_CUSTOM_BROADCAST:String =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"
    }

    override fun onDestroy() {
        unregisterReceiver(mReciever)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReciever)
        super.onDestroy()
    }
}