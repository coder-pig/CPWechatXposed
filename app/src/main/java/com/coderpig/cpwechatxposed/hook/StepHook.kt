package com.coderpig.cpwechatxposed.hook

import android.annotation.SuppressLint
import com.coderpig.cpwechatxposed.Constants
import com.coderpig.cpwechatxposed.XposedInit
import com.coderpig.cpwechatxposed.XposedInit.Companion.xsp
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge

/**
 * 描述：步数Hook
 *
 * @author jay on 2018/4/28 12:28
 */
object StepHook {
    @SuppressLint("PrivateApi")
    fun hook() {
        val c1 = Class.forName("android.hardware.SystemSensorManager\$SensorEventQueue")
        XposedBridge.hookAllMethods(c1, "dispatchSensorEvent", object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam ) {
                xsp.reload()
                if (XposedInit.xsp.getBoolean(Constants.IS_STEP_OPEN, false)) {
                    (param.args[1] as FloatArray)[0] = (param.args[1] as FloatArray)[0] * (xsp.getString(Constants.CUR_STEP_MULT, "1").toInt())
                }
                super.beforeHookedMethod(param)
            }
        })
    }
}