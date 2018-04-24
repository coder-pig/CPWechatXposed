package com.coderpig.cpwechatxposed

import android.annotation.SuppressLint
import android.content.Context


/**
 * 描述：
 *
 * @author CoderPig on 2018/04/24 17:00.
 */
class SharedPreferenceUtils {
    companion object {
        @SuppressLint("ApplySharedPref")
        fun putSP(key: String, value: Any) {
            val type = value.javaClass.simpleName
            val sharedPreferences = App.instance.getSharedPreferences("config",Context.MODE_WORLD_READABLE)
            val editor = sharedPreferences.edit()
            when (type) {
                "Integer" -> editor.putInt(key, value as Int)
                "Boolean" -> editor.putBoolean(key, value as Boolean)
                "String" -> editor.putString(key, value as String)
                "Float" -> editor.putFloat(key, value as Float)
                "Long" -> editor.putLong(key, value as Long)
            }
            editor.apply()
        }

        fun getSP(context: Context, key: String, defValue: Any): Any? {
            val type = defValue.javaClass.simpleName
            val sharedPreferences = App.instance.getSharedPreferences("config",Context.MODE_WORLD_READABLE)
            return when (type) {
                "Integer" -> sharedPreferences.getInt(key, defValue as Int)
                "Boolean" -> sharedPreferences.getBoolean(key, defValue as Boolean)
                "String" -> sharedPreferences.getString(key, defValue as String)
                "Float" -> sharedPreferences.getFloat(key, defValue as Float)
                "Long" -> sharedPreferences.getLong(key, defValue as Long)
                else -> null
            }
        }
    }
}