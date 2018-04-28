package com.coderpig.cpwechatxposed

/**
 * 描述：相关常量
 *
 * @author CoderPig on 2018/04/24 15:11.
 */
class Constants {
    companion object {
        val IS_STEP_OPEN = "is_step_open"   //是否打开步数助手
        val CUR_STEP_MULT = "cur_step_multiple" //步数助手的倍数
        val IS_CQ_OPEN = "is_cq_open"   //是否打开猜拳助手
        val IS_TZ_OPEN = "is_tz_open"   //是够打开投骰子助手
        val CUR_CQ_NUM = "cur_cq_num"   //猜拳数字0，1，2对应剪刀石头布
        val CUR_TZ_NUM = "cur_tz_num"   //骰子数字0-5，对应1-6点
    }
}