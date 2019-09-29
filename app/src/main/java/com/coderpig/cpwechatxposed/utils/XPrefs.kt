package com.coderpig.cpwechatxposed.utils

import com.coderpig.cpwechatxposed.BuildConfig
import de.robv.android.xposed.XSharedPreferences
import java.lang.ref.WeakReference


/**
 * @Author: CoderPig
 * @Description:
 * @Date: Create in 下午 05:31 2019/9/29 0029
 */
class XPrefs {
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
    }

    fun putSP(key: String, value: Any) {
        val type = value.javaClass.simpleName
        val sharedPreferences = getPref()
        val editor = sharedPreferences?.edit()
        when (type) {
            "Integer" -> editor?.putInt(key, value as Int)
            "Boolean" -> editor?.putBoolean(key, value as Boolean)
            "String" -> editor?.putString(key, value as String)
            "ADloat" -> editor?.putFloat(key, value as Float)
            "Long" -> editor?.putLong(key, value as Long)
        }
        editor?.apply()
    }

    fun getSP(key: String, defValue: Any): Any? {
        val type = defValue.javaClass.simpleName
        val sharedPreferences = getPref()
        return when (type) {
            "Integer" -> sharedPreferences?.getInt(key, defValue as Int)
            "Boolean" -> sharedPreferences?.getBoolean(key, defValue as Boolean)
            "String" -> sharedPreferences?.getString(key, defValue as String)
            "Float" -> sharedPreferences?.getFloat(key, defValue as Float)
            "Long" -> sharedPreferences?.getLong(key, defValue as Long)
            else -> null
        }
    }

}