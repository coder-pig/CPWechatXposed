package com.coderpig.cpwechatxposed

import android.annotation.SuppressLint
import de.robv.android.xposed.*
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
            "com.tencent.mm" -> {
                val c1 = Class.forName("android.hardware.SystemSensorManager\$SensorEventQueue")
                XposedBridge.hookAllMethods(c1, "dispatchSensorEvent", object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        xsp.reload()
                        if (xsp.getBoolean(Constants.IS_STEP_OPEN, false)) {
                            val muti = xsp.getString(Constants.CUR_STEP_MULT, "1").toInt()
                            (param.args[1] as FloatArray)[0] = (param.args[1] as FloatArray)[0] * muti
                        }
                        super.beforeHookedMethod(param)
                    }
                })
                val c2 = XposedHelpers.findClass("com.tencent.mm.sdk.platformtools.bh",lpparam.classLoader)
                XposedHelpers.findAndHookMethod(c2, "eE", Int::class.java, Int::class.java, object : XC_MethodHook() {
                    @Throws
                    override fun afterHookedMethod(param: MethodHookParam) {
                        xsp.reload()
                        when(param.args[0]) {
                            2 -> {
                                if(xsp.getBoolean(Constants.IS_CQ_OPEN, false)) {
                                    val cq = xsp.getInt(Constants.CUR_CQ_NUM, 0)
                                        param.result = cq
                                }
                            }
                            5 -> {
                                if(xsp.getBoolean(Constants.IS_TZ_OPEN, false)) {
                                    val tz = xsp.getInt(Constants.CUR_TZ_NUM, 0)
                                        param.result = tz
                                }
                            }
                        }
                        super.afterHookedMethod(param)
                    }
                })
            }
            //Hook掉模块验证方法返回true
            "com.coderpig.cpwechatxposed" -> {
                XposedHelpers.findAndHookMethod("com.coderpig.cpwechatxposed.SettingActivity",
                        lpparam.classLoader, "isModuleActive",XC_MethodReplacement.returnConstant(true))
            }
        }
    }
}