package com.coderpig.cpwechatxposed

import android.app.Application
import com.orhanobut.hawk.Hawk
import kotlin.properties.Delegates

/**
 * 描述：应用的APP类
 *
 * @author CoderPig on 2018/04/24 15:22.
 */
class App : Application() {
    companion object {
        var instance by Delegates.notNull<App>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}