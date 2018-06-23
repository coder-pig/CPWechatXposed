package com.coderpig.cpwechatxposed.hook

import android.util.Log
import com.coderpig.cpwechatxposed.Constants
import com.coderpig.cpwechatxposed.XposedInit
import com.coderpig.cpwechatxposed.XposedInit.Companion.xsp
import com.coderpig.cpwechatxposed.utils.toArray
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.callbacks.XC_LoadPackage

/**
 * 描述：下厨房Xposed检测弹窗Hook
 *
 * @author jay on 2018/6/23 18:15
 */
object XiaChuFangHook{
    fun hook(lpparam: XC_LoadPackage.LoadPackageParam) {

        findAndHookMethod("java.lang.Throwable", lpparam.classLoader, "getStackTrace", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                xsp.reload()
                if(XposedInit.xsp.getBoolean(Constants.IS_XCF_OPEN, false)) {
                    val result = mutableListOf<StackTraceElement>()
                    for (stackTraceElement in (param.result as Array<StackTraceElement>)) {
                        val className = stackTraceElement.className
                        val methodName = stackTraceElement.methodName
                        if(className != null && methodName != null) {
                            if( !className.contains("XposedBridge")  && !methodName.contains("XposedBridge")) {
                                result.add(stackTraceElement)
                            }
                        }
                    }
                    param.result = toArray<StackTraceElement>(result)
                }
                super.afterHookedMethod(param)
            }
        })
    }
}