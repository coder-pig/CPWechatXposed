package com.coderpig.cpwechatxposed.hook

import android.content.ContentValues
import android.util.Log
import com.coderpig.cpwechatxposed.Constants
import com.coderpig.cpwechatxposed.XposedInit
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import android.os.Message

/**
 *  @Author: CoderPig
 *  @CreateTime: 2019-02-02
 *  @Description: 微信抢红包Hook
 */
object RedPacketHook {
    fun hook(lpparam: XC_LoadPackage.LoadPackageParam) {
        try {
            val clazz = XposedHelpers.findClass("com.tencent.wcdb.database.SQLiteDatabase", lpparam.classLoader)
            XposedHelpers.findAndHookMethod(clazz, "insert",
                    String::class.java, String::class.java, ContentValues::class.java,
                    object : XC_MethodHook() {
                        override fun beforeHookedMethod(param: MethodHookParam) {
                            XposedInit.xsp.reload()
                            Log.e("微信", "更新一条信息~")
                            Log.e("微信", param.args[0].toString())
                            Log.e("微信", param.args[1].toString())
                            for (item in (param.args[2] as ContentValues).valueSet()) {
                                Log.e("微信", item.key + "==>" + item.value.toString())
                            }
                            super.beforeHookedMethod(param)
                        }
                    })
        } catch (e: Error) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

//                    if ("digest=[微信红包]" in param.args[1].toString() && "我给你发了一个红包，赶紧去拆!" in param.args[1].toString()) {
//                        Log.e("微信", "收到微信红包")
//                    }