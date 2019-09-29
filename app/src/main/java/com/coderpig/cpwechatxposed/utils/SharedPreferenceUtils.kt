package com.coderpig.cpwechatxposed.utils

import android.annotation.SuppressLint
import android.content.Context
import android.preference.PreferenceManager
import com.coderpig.cpwechatxposed.App
import com.coderpig.cpwechatxposed.BuildConfig
import de.robv.android.xposed.XSharedPreferences
import java.io.File
import java.lang.ref.WeakReference


/**
 * 描述：SharedPreference工具类
 *
 * @author CoderPig on 2018/04/24 17:00.
 */
class SharedPreferenceUtils {
    companion object {
        var xSharedPreferences: WeakReference<XSharedPreferences> = WeakReference<XSharedPreferences>(null)

        fun getPref(): XSharedPreferences? {
            var preferences = xSharedPreferences.get()
            if (preferences == null) {
                preferences = XSharedPreferences(BuildConfig.APPLICATION_ID)
                preferences.makeWorldReadable()
                preferences.reload()
                xSharedPreferences = WeakReference(preferences)
            } else {
                preferences.reload()
            }
            return preferences
        }


        @SuppressLint("ApplySharedPref")
        fun putSP(key: String, value: Any) {
            val type = value.javaClass.simpleName
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.instance)
            val editor = sharedPreferences?.edit()
            when (type) {
                "Integer" -> editor?.putInt(key, value as Int)
                "Boolean" -> editor?.putBoolean(key, value as Boolean)
                "String" -> editor?.putString(key, value as String)
                "ADloat" -> editor?.putFloat(key, value as Float)
                "Long" -> editor?.putLong(key, value as Long)
            }
            editor?.apply()
            setWorldReadable()
        }

        fun getSP(key: String, defValue: Any): Any? {
            val type = defValue.javaClass.simpleName
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.instance)
            return when (type) {
                "Integer" -> sharedPreferences?.getInt(key, defValue as Int)
                "Boolean" -> sharedPreferences?.getBoolean(key, defValue as Boolean)
                "String" -> sharedPreferences?.getString(key, defValue as String)
                "Float" -> sharedPreferences?.getFloat(key, defValue as Float)
                "Long" -> sharedPreferences?.getLong(key, defValue as Long)
                else -> null
            }
        }

        @SuppressLint("SetWorldReadable", "WorldReadableFiles")
        private fun setWorldReadable() {
            val dataDir = File(App.instance.applicationContext.applicationInfo.dataDir)
            val prefsDir = File(dataDir, "shared_prefs")
            val prefsFile = File(prefsDir, BuildConfig.APPLICATION_ID + "_preferences.xml")
            if (prefsFile.exists()) {
                for (file in arrayOf(dataDir, prefsDir, prefsFile)) {
                    file.setReadable(true, false)
                    file.setExecutable(true, false)
                }
            }
        }

    }


}
