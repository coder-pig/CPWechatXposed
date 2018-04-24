package com.coderpig.cpwechatxposed

import android.annotation.SuppressLint
import android.util.Log
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage
import kotlin.properties.Delegates

/**
 * 描述：
 *
 * @author CoderPig on 2018/04/24 16:03.
 */
class XposedInit : IXposedHookLoadPackage {
    companion object {
        var xsp by Delegates.notNull<XSharedPreferences>()
    }

    init {
        xsp = XSharedPreferences(BuildConfig.APPLICATION_ID, "config")
        xsp.makeWorldReadable()
    }

    @SuppressLint("PrivateApi")
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (lpparam.packageName) {
            "com.tencent.mm", "com.alibaba.android.rimet" -> {
                val c = Class.forName("android.hardware.SystemSensorManager\$SensorEventQueue")
                XposedBridge.hookAllMethods(c, "dispatchSensorEvent", object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        xsp.reload()
                        if (xsp.getBoolean(Constants.IS_STEP_OPEN, false)) {
                            val muti = xsp.getString(Constants.CUR_STEP_MULT, "1").toInt()
                            (param.args[1] as FloatArray)[0] = (param.args[1] as FloatArray)[0] * muti
                        }
                        super.beforeHookedMethod(param)
                    }
                })
            }
        }
    }
}