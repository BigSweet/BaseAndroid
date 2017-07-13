package cn.primedu.m.baselib.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/4/12
 */
public class JumpUtils implements Key {
    public final static String PK_NAME = "cn.primedu.m.firepowerschool_android";

    public static void toActivity(final Activity activity, Intent intent, String clsSimpleName) {
        toActivityDefaultAnim(activity, intent, clsSimpleName);
        //有需要就加上动画
//        activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }


    public static void toActivityDefaultAnim(final Activity activity, Intent intent, String clsSimpleName) {
        if (null == activity || TextUtils.isEmpty(clsSimpleName)) return;
        String fullName = clsSimpleName;
        if (fullName == null) {
            return;
        }

        if (null == intent) intent = new Intent();

        intent.setComponent(new ComponentName(PK_NAME, fullName));
        activity.startActivity(intent);
    }

    public static void toActivityForResult(Activity activity, String clsSimpleName, Bundle bundle, int requestCode) {
        toActivityForResultDefaultAnim(activity, clsSimpleName, bundle, requestCode);

    }

    public static void toActivityForResultDefaultAnim(Activity activity, String clsSimpleName, Bundle bundle, int requestCode) {
        if (null == activity || TextUtils.isEmpty(clsSimpleName)) return;

        String fullName = clsSimpleName;
        if (fullName == null) {
            LogUtils.e("errr------, cannot find" + clsSimpleName);
            return;
        }

        Intent intent = new Intent();
        intent.setComponent(new ComponentName(PK_NAME, fullName));


        if (null != bundle) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }
}
