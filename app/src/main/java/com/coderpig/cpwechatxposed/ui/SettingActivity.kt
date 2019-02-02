package com.coderpig.cpwechatxposed.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.coderpig.cpwechatxposed.Constants
import com.coderpig.cpwechatxposed.R
import com.coderpig.cpwechatxposed.utils.SharedPreferenceUtils
import com.coderpig.cpwechatxposed.utils.shortToast
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * 描述：Xposed设置页
 *
 * @author CoderPig on 2018/04/24 14:45.
 */

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initView()
        if (!isModuleActive()) shortToast("Xposed模块未激活!")
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        // 步数助手
        tv_mutiple.text = ((resources.getString(R.string.cur_multiple) +
                (SharedPreferenceUtils.getSP(Constants.CUR_STEP_MULT, "0").toString().toInt() + 1)))
        cb_step_switch.isChecked = SharedPreferenceUtils.getSP(Constants.IS_STEP_OPEN, false) as Boolean

        cb_step_switch.setOnCheckedChangeListener { _, isChecked ->
            SharedPreferenceUtils.putSP(Constants.IS_STEP_OPEN, isChecked)
        }
        sb_multiple.progress = SharedPreferenceUtils.getSP(Constants.CUR_STEP_MULT, "1").toString().toInt() + 1
        sb_multiple.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                tv_mutiple.text = resources.getString(R.string.cur_multiple) + (progress + 1)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                SharedPreferenceUtils.putSP(Constants.CUR_STEP_MULT, "" + seekBar.progress)
            }
        })

        //猜拳助手
        cb_cq.isChecked = SharedPreferenceUtils.getSP(Constants.IS_CQ_OPEN, false) as Boolean
        when (SharedPreferenceUtils.getSP(Constants.CUR_CQ_NUM, 0)) {
            0 -> rb_jd.isChecked = true
            1 -> rb_st.isChecked = true
            2 -> rb_b.isChecked = true
        }
        cb_cq.setOnCheckedChangeListener { _, isChecked ->
            SharedPreferenceUtils.putSP(Constants.IS_CQ_OPEN, isChecked)
        }
        rg_cq.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_jd -> SharedPreferenceUtils.putSP(Constants.CUR_CQ_NUM, 0)
                R.id.rb_st -> SharedPreferenceUtils.putSP(Constants.CUR_CQ_NUM, 1)
                R.id.rb_b -> SharedPreferenceUtils.putSP(Constants.CUR_CQ_NUM, 2)
            }
        }

        //骰子助手
        cb_tz.isChecked = SharedPreferenceUtils.getSP(Constants.IS_TZ_OPEN, false) as Boolean
        when (SharedPreferenceUtils.getSP(Constants.CUR_TZ_NUM, 0)) {
            0 -> rb_tz_1.isChecked = true
            1 -> rb_tz_2.isChecked = true
            2 -> rb_tz_3.isChecked = true
            3 -> rb_tz_4.isChecked = true
            4 -> rb_tz_5.isChecked = true
            5 -> rb_tz_6.isChecked = true
        }
        cb_tz.setOnCheckedChangeListener { _, isChecked ->
            SharedPreferenceUtils.putSP(Constants.IS_TZ_OPEN, isChecked)
        }

        rg_tz.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_tz_1 -> SharedPreferenceUtils.putSP(Constants.CUR_TZ_NUM, 0)
                R.id.rb_tz_2 -> SharedPreferenceUtils.putSP(Constants.CUR_TZ_NUM, 1)
                R.id.rb_tz_3 -> SharedPreferenceUtils.putSP(Constants.CUR_TZ_NUM, 2)
                R.id.rb_tz_4 -> SharedPreferenceUtils.putSP(Constants.CUR_TZ_NUM, 3)
                R.id.rb_tz_5 -> SharedPreferenceUtils.putSP(Constants.CUR_TZ_NUM, 4)
                R.id.rb_tz_6 -> SharedPreferenceUtils.putSP(Constants.CUR_TZ_NUM, 5)
            }
        }

        //微信防撤回
        cb_wx_fch.isChecked = SharedPreferenceUtils.getSP(Constants.IS_WX_FCH_OPEN, false) as Boolean
        cb_wx_fch.setOnCheckedChangeListener { _, isChecked -> SharedPreferenceUtils.putSP(Constants.IS_WX_FCH_OPEN, isChecked) }
    }


    //判断模块是否生效的方法，XposedInit里把这个方法Hook掉返回true
    private fun isModuleActive() = false

}