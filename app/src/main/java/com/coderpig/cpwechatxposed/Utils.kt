package com.coderpig.cpwechatxposed

import android.widget.Toast

/**
 * 描述：
 *
 * @author CoderPig on 2018/04/27 12:26.
 */
fun shortToast(msg: String) = Toast.makeText(App.instance, msg, Toast.LENGTH_SHORT).show()

fun longToast(msg: String) = Toast.makeText(App.instance, msg, Toast.LENGTH_LONG).show()