package cn.primedu.m.baselib.util;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/5/8
 */
public class TimeUtil {
    /**
     * 时间的处理
     *
     * @param time
     * @return
     */
    public static String getTimeFromInt(long time) {

        if (time <= 0) {
            return "0'00";
        }
        long secondnd = (time / 1000) / 60;
        long million = (time / 1000) % 60;
        String f = String.valueOf(secondnd);
        String m = million >= 10 ? String.valueOf(million) : "0"
                + String.valueOf(million);
        return f + "'" + m;
    }
}
