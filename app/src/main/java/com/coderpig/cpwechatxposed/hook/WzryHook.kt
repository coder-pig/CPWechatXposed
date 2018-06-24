package com.coderpig.cpwechatxposed.hook

import android.os.Build
import com.coderpig.cpwechatxposed.Constants
import com.coderpig.cpwechatxposed.XposedInit
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

/**
 * 描述：王者荣耀Hook变成OPPO R11s
 * 使用方式：开启后进入游戏，打开高帧率模式，退出，清除缓存，再重新进入就可以了
 *
 * @author jay on 2018/6/23 18:15
 */
object WzryHook {
    fun hook() {
            XposedHelpers.setStaticObjectField(Build::class.java, Constants.MANUFACTURER, "OPPO")
            XposedHelpers. setStaticObjectField(Build::class.java, Constants.BRAND, "OPPO")
            XposedHelpers.  setStaticObjectField(Build::class.java, Constants.PRODUCT, "R11 Plus")
            XposedHelpers.setStaticObjectField(Build::class.java, Constants.DEVICE, "R11 Plus")
            XposedHelpers.setStaticObjectField(Build::class.java, Constants.MODEL, "OPPO R11 Plus")
            //应对反射获取机型的情况
            val c = Class.forName("android.os.SystemProperties")
            val m = c.getDeclaredMethod("native_get", String::class.java, String::class.java)
            m.isAccessible = true
            XposedBridge.hookMethod(m, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam) {
                    XposedInit.xsp.reload()
                    if(XposedInit.xsp.getBoolean(Constants.IS_XCF_OPEN, false)) {
                        when (param.args[0].toString()) {
                            "ro.product.manufacturer", "ro.product.brand" -> param.result = "OPPO"
                            "ro.product.name", "ro.product.device" -> param.result = "R11 Plus"
                            "ro.product.model" -> param.result = "OPPO R11 Plus"
                        }
                    }
                }
            })
        }
}