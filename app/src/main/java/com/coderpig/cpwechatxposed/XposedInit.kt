package com.coderpig.cpwechatxposed

import com.coderpig.cpwechatxposed.hook.*
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import kotlin.properties.Delegates

/**
 * 描述：Xposed 处理类
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

    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (lpparam.packageName) {
            //微信相关
            "com.tencent.mm" -> {
                xsp.reload()
                StepHook.hook() //步数助手
                EmojiGameHook.hook(lpparam) //猜拳和投骰子助手
                RevokeMsgHook.hook(lpparam) //消息防撤回
                RedPacketHook.hook(lpparam) //抢红包
            }
             //Hook掉模块验证方法返回true，验证模块是否生效
            "com.coderpig.cpwechatxposed" -> {
                XposedHelpers.findAndHookMethod("com.coderpig.cpwechatxposed.ui.SettingActivity",
                        lpparam.classLoader, "isModuleActive", XC_MethodReplacement.returnConstant(true))
            }
        }
    }
}