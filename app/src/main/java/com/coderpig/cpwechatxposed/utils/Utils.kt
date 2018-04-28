package com.coderpig.cpwechatxposed.utils

import android.widget.Toast
import com.coderpig.cpwechatxposed.App

/**
 * 描述：工具方法扩展文件
 *
 * @author CoderPig on 2018/04/27 12:26.
 */
fun shortToast(msg: String) = Toast.makeText(App.instance, msg, Toast.LENGTH_SHORT).show()

fun longToast(msg: String) = Toast.makeText(App.instance, msg, Toast.LENGTH_LONG).show()