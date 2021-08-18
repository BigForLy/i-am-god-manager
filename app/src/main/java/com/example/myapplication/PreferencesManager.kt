package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

/* класс для сохранения пользовательских настроек  */
class UserSettingsStorage(activity: Activity) {

    var appPreferencesConnection = "Connection"
    var appPreferencesUserId = "UserId"

    private val appPreferences = "MySettings"
    private val sharedPref : SharedPreferences = activity.getSharedPreferences(appPreferences, Context.MODE_PRIVATE)

    fun save(Parameter : String, Value : String){
        with (sharedPref.edit()) {
            putString(Parameter, Value)
            commit()
        }
    }

    fun load(Parameter: String): String {
        return sharedPref.getString(Parameter, "").toString()
    }
}