package cn.primedu.m.baselib.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/8/24
 */
public class ProgressDialogUtils {

    Context mContext;
    ProgressDialog progressDialog;

    public ProgressDialogUtils(Context context) {
        mContext = context;
        if (context != null) {
            progressDialog = new ProgressDialog(context);
        }
    }



    public  void showDialog(String Message, boolean cancle) {
        progressDialog.setCancelable(cancle);
        progressDialog.setMessage(Message);
        progressDialog.show();
    }

    public  void showDialog(String Message) {
        progressDialog.setCancelable(true);
        progressDialog.setMessage(Message);
        progressDialog.show();
    }

    public  void DissDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
