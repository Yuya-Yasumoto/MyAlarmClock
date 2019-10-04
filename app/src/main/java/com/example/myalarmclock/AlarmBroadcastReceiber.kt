package com.example.myalarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

const val ON_RECEIVE = "onReceive"

class AlarmBroadcastReceiber : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val mainIntent = Intent(context,MainActivity::class.java)
            .putExtra(ON_RECEIVE,true) //任意だが、Broadcastレシーバーから呼びだされた事を明示的にする
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) //Broadcastレシーバーから呼び出すときに必要
        context.startActivity(mainIntent)

        //This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context,"ｱﾗｰﾑを受信しました。",Toast.LENGTH_SHORT)
            .show()


    }
}
