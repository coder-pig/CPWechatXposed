package com.coderpig.cpwechatxposed

import de.robv.android.xposed.XSharedPreferences

/**
 * 描述：
 *
 * @author CoderPig on 2018/04/24 16:43.
 */
class XPreferenceUtils {
    companion object {
        val IS_STEP_OPEN = "is_step_open"
        val CUR_STEP_MULT = "cur_step_multiple"
        private var xp: XSharedPreferences? = null

        fun getInstance(): XSharedPreferences {
            if (xp == null) {
                xp = XSharedPreferences("com.coderpig.cpwechatxposed", "config")
                (xp as XSharedPreferences).makeWorldReadable()
            } else {
                xp?.reload()
            }
            return xp as XSharedPreferences
        }

    }


}