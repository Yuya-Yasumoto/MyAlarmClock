package com.example.myalarmclock

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.time.MonthDay
import java.util.*

class TimeAlertDialog : DialogFragment(){
    //interfaceを作成
    interface Listener {
        fun getUp()
        fun snooze()
    }

    //Listenerクラスを実装したクラスを格納する変数
    private var listener: Listener? = null

    //onAttach()..　Activityからフラグメントが呼ばれたとき
    //contextは、このダイアログを呼び出したActivityのコンテキストが渡される
    //contextとは、アプリの"状況"を保持した抽象クラスと考えればよい
    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {
            is Listener -> listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //builderパターン
        //requireActivity()…フラグメントを呼び出したアクティビティ
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("時間になりました")

        //ダイアログに表示する１番目のボタン
        builder.setPositiveButton("起きる"){
            dialog, which ->  //OnClick()処理。which:タップしたダイアログ,タップしたボタンの種類
            listener?.getUp()
        }
        //ダイアログに表示する１番目のボタン
        builder.setNegativeButton("あと5分寝る"){
            dialog, which ->
            listener?.snooze()
        }
        //ダイアログに表示する３番目のボタン
        //builder.setNeutralButton("なんやろ"){
        //}

        //onCreateDialogでは、AlertDialogオブジェクトを返すきまり
        return builder.create()
    }

}

class DatePickerFragment:DialogFragment(),
    DatePickerDialog.OnDateSetListener{

    interface OnDateSelectedListener{
        fun onSelected(year:Int,month:Int,date:Int)
    }

    private var listener:OnDateSelectedListener?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context){
            is OnDateSelectedListener -> listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val date = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireActivity(),this,year,month,date)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener?.onSelected(year,month,dayOfMonth)
    }

}

class TimePickerFragment:DialogFragment(),
        TimePickerDialog.OnTimeSetListener{

    interface OntimeSelectedListener{
        fun onSelected(hourOfDay:Int,minute:Int)
    }

    private var listener:OntimeSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context){
            is OntimeSelectedListener -> listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(context,this,hour,minute,true)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        listener?.onSelected(hourOfDay,minute)
    }
}