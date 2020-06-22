package com.example.investigation.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.investigation.view.MyChatListPageFragment


class SharedPreferenceHelper(context: Context) {


    private val PREF_UNAME="Username"
    private val PREF_UID="0"
    private val prefs= PreferenceManager.getDefaultSharedPreferences(context)

    fun saveUname(uname:String) = prefs.edit().putString(PREF_UNAME,uname).apply()


    fun saveUID(uid:String) = prefs.edit().putString(PREF_UID,uid).apply()



    fun getUName() = prefs.getString(PREF_UNAME,null)
    fun getUID() = prefs.getString(PREF_UID,null)



}