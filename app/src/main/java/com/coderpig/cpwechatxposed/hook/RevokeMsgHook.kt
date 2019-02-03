package com.coderpig.cpwechatxposed.hook

import android.content.ContentValues
import android.util.Log
import com.coderpig.cpwechatxposed.Constants
import com.coderpig.cpwechatxposed.XposedInit
import com.coderpig.cpwechatxposed.XposedInit.Companion.xsp
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.io.File


/**
 * 描述：微信信息防撤回Hook
 *
 * @author jay on 2018/6/28 12:28
 */
object RevokeMsgHook {

    val msgContainer = mutableMapOf<Long, Any>()
    var insertAny: Any? = null

    fun hook(lpparam: XC_LoadPackage.LoadPackageParam) {
        // Hook 撤回信息的方法
        try {
            val clazz = XposedHelpers.findClass("com.tencent.wcdb.database.SQLiteDatabase", lpparam.classLoader)
            XposedHelpers.findAndHookMethod(clazz, "updateWithOnConflict", String::class.java, ContentValues::class.java,
                    String::class.java, Array<String>::class.java, Int::class.java, object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    xsp.reload()
                    if (param.args[0] == "message") {
                        val contentValues = param.args[1] as ContentValues
                        val content = contentValues.getAsString("content")
                        if (XposedInit.xsp.getBoolean(Constants.IS_WX_FCH_OPEN, false) && contentValues.getAsInteger("type") == 10000 && content != "你撤回了一条消息") {
                            val msgId = contentValues.getAsLong("msgId")
                            val msg = msgContainer[msgId]
                            XposedHelpers.setObjectField(msg, "field_content", "\uD83D\uDC37 拦截到=== " + content.substring(1, content.indexOf("撤") - 2) + " ===撤回的 \uD83D\uDCA9 ")
                            XposedHelpers.setIntField(msg, "field_type", contentValues.getAsInteger("type"))
                            XposedHelpers.setLongField(msg, "field_createTime", XposedHelpers.getLongField(msg, "field_createTime") + 1L)
                            XposedHelpers.callMethod(insertAny, "c", msg, false)
                            param.result = 1
                        }
                    }
                    super.beforeHookedMethod(param)
                }
            })

            // Hook 删除资源文件数据库记录的方法
            XposedHelpers.findAndHookMethod(clazz, "delete", String::class.java, String::class.java, Array<String>::class.java, object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    val tableName = param.args[0].toString()
                    if (XposedInit.xsp.getBoolean(Constants.IS_WX_FCH_OPEN, false) && tableName.contains("voiceinfo") || tableName.contains("ImgInfo2") ||
                            tableName.contains("videoinfo2") || tableName.contains("WxFileIndex2")) {
                        param.result = 1
                    }
                    super.beforeHookedMethod(param)
                }
            })

            // Hook 删除文件的方法
            XposedHelpers.findAndHookMethod(File::class.java, "delete", object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    val filePath = (param.thisObject as File).absolutePath
                    if (XposedInit.xsp.getBoolean(Constants.IS_WX_FCH_OPEN, false) && filePath.contains("/image2/") || filePath.contains("/voice2/") || filePath.contains("/video/")) {
                        param.result = true
                    }
                    super.afterHookedMethod(param)
                }
            })

            // Hook 插入信息的方法
            val cla = XposedHelpers.findClass("com.tencent.mm.storage.bj", lpparam.classLoader)
            XposedBridge.hookAllMethods(cla, "c", object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam) {
                    insertAny = param.thisObject
                    val bd = param.args[0]
                    val field_msgId = XposedHelpers.getLongField(bd, "field_msgId")
                    msgContainer[field_msgId] = bd
                    super.afterHookedMethod(param)
                }
            })

        } catch (e: Error) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}