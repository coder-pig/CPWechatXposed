package com.coderpig.cpwechatxposed

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * 描述：
 *
 * @author CoderPig on 2018/04/24 14:45.
 */

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        tv_mutiple.text = ((resources.getString(R.string.cur_multiple) +
                (SharedPreferenceUtils.getSP(App.instance, Constants.CUR_STEP_MULT, "0").toString().toInt() + 1)))
        cb_step_switch.isChecked = SharedPreferenceUtils.getSP(App.instance, Constants.IS_STEP_OPEN, false) as Boolean

        cb_step_switch.setOnCheckedChangeListener({ _, isChecked ->
            SharedPreferenceUtils.putSP(Constants.IS_STEP_OPEN, isChecked)
        })
        sb_multiple.progress = SharedPreferenceUtils.getSP(App.instance, Constants.CUR_STEP_MULT, "1").toString().toInt() + 1
        sb_multiple.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                tv_mutiple.text = resources.getString(R.string.cur_multiple) + (progress + 1)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                SharedPreferenceUtils.putSP(Constants.CUR_STEP_MULT, "" + seekBar.progress)
            }
        })
    }

}