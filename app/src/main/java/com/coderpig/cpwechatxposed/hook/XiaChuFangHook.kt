package com.coderpig.cpwechatxposed.hook

import android.util.Log
import com.coderpig.cpwechatxposed.utils.toArray
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.callbacks.XC_LoadPackage


object XiaChuFangHook{
    fun hook(lpparam: XC_LoadPackage.LoadPackageParam) {
        findAndHookMethod("java.lang.Throwable", lpparam.classLoader, "getStackTrace", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val result = mutableListOf<StackTraceElement>()
                for (stackTraceElement in (param.result as Array<StackTraceElement>)) {
                    val className = stackTraceElement.className
                    val methodName = stackTraceElement.methodName
                    if(className != null && methodName != null) {
                        if(!className.contains("XposedBridge")  && !methodName.contains("XposedBridge")) {
                            result.add(stackTraceElement)
                        }
                    }
                }
                param.result = toArray<StackTraceElement>(result)
                super.afterHookedMethod(param)
            }
        })
    }
}