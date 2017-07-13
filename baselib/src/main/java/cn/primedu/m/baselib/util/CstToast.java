package cn.primedu.m.baselib.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import cn.primedu.m.baselib.R;


/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/7/10
 */
public class CstToast extends Toast {
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public CstToast(Context context) {
        super(context);
    }

/*
    public static void makeText(Context context, CharSequence text, int duration) {
        final Toast result = new Toast(context);
        //获取LayoutInflater对象
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //获得屏幕的宽度
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        //由layout文件创建一个View对象
        View view = inflater.inflate(R.layout.cst_toast, null);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView toastTextView = (TextView) view.findViewById(R.id.tv_toast);
        //设置TextView的宽度为 屏幕宽度
        toastTextView.setLayoutParams(layoutParams);
        toastTextView.setText(text);

        result.setView(view);
        result.setGravity(Gravity.CENTER, 0, height * 3 / 5);
        result.setDuration(duration);
        result.show();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                result.cancel();
                result.cancel();
            }
        }, duration );
    }*/

    public static  void showMyToast(Context context,String text, final int duration) {
        final Toast result = Toast.makeText(context,text,Toast.LENGTH_LONG);
        //获取LayoutInflater对象
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //获得屏幕的宽度
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        //由layout文件创建一个View对象
        View view = inflater.inflate(R.layout.cst_toast, null);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView toastTextView = (TextView) view.findViewById(R.id.tv_toast);
        //设置TextView的宽度为 屏幕宽度
        toastTextView.setLayoutParams(layoutParams);
        toastTextView.setText(text);

        result.setView(view);
        result.setGravity(Gravity.CENTER, 0, height * 3 / 10);
//        result.setDuration(duration);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                result.show();
            }
        }, 0);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                result.cancel();
                result.cancel();
            }
        }, duration );
    }

}
