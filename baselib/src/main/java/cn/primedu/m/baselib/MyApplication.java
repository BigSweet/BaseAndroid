package cn.primedu.m.baselib;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import cn.primedu.m.baselib.base.SwToast;
import cn.primedu.m.baselib.util.LogUtils;


/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@imcoming.cn
 * 时间: 2017/3/3
 */

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //只在debug下才打印出日志
        LogUtils.setDebug(Config.isDebug);
        Fresco.initialize(mContext);
        SwToast.Init(mContext);
    }

    /**
     * @return 全局的上下文
     */
    public static Context getmContext() {
        return mContext;
    }
}
