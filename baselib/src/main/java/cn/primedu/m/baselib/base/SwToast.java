package cn.primedu.m.baselib.base;

import android.content.Context;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/5/23
 */
public class SwToast {
    private static Context mContext;
    private static Toast toast;
    public static void Init(Context context) {
        mContext = context;
    }


    public synchronized static void show(String content) {
        if (toast == null) {
            toast= Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
        }else {
            toast.setText(content);
        }
        toast.show();
    }

    public synchronized static void showLong(String content) {

        if (toast == null) {
            toast= Toast.makeText(mContext, content, Toast.LENGTH_LONG);
        }else {
            toast.setText(content);
        }
        toast.show();
    }

    public static  void showMyToast(final Toast toast, final int cnt) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt );
    }
}
