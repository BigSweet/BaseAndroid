package cn.primedu.m.baselib.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import cn.primedu.m.baselib.R;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/6/23
 */
public class CommonDialog {
    static Dialog dialog;

    public static void showdilog(Context context) {
        dialog = new Dialog(context, R.style.MyDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.wait_dialog, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public  static  void DismissDialog() {
        dialog.dismiss();
    }
}
