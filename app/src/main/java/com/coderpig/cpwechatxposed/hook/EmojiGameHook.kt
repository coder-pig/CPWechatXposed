package com.coderpig.cpwechatxposed.hook

import com.coderpig.cpwechatxposed.Constants
import com.coderpig.cpwechatxposed.XposedInit.Companion.xsp
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

/**
 * 描述：猜拳和投骰子Hook
 *
 * @author jay on 2018/4/28 15:42
 */
object EmojiGameHook {
    fun hook(lpparam: XC_LoadPackage.LoadPackageParam) {
        val clazz = XposedHelpers.findClass("com.tencent.mm.sdk.platformtools.bj", lpparam.classLoader)
        XposedHelpers.findAndHookMethod(clazz, "eV", Int::class.java, Int::class.java, object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                xsp.reload()
                if (param.args[0] == 2 && xsp.getBoolean(Constants.IS_CQ_OPEN, false))
                    param.result = xsp.getInt(Constants.CUR_CQ_NUM, 0)
                else if (param.args[0] == 5 && xsp.getBoolean(Constants.IS_TZ_OPEN, false))
                    param.result = xsp.getInt(Constants.CUR_TZ_NUM, 0)
                super.afterHookedMethod(param)
            }
        })
    }
}