package cn.primedu.m.baselib.util;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ${sunwentao} on 2017/4/6.
 */

public class NoNullUtils {


    /**
     * 简单设置文字
     *
     * @param t
     * @param s
     */
    public static void setText(TextView t, String s) {
        if (null != t) {
            if (null == s) {
                s = "";
            }
            t.setText(toDBC(s.trim()));
        }
    }

    public static void setText(TextView t, CharSequence s) {
        if (null != t) {
            if (null == s) {
                s = "";
            }
            t.setText(toDBC(s.toString().trim()));
        }
    }

    /**
     * 将所有文字转半角
     *
     * @param input
     * @return
     */
    public static String toDBC(String input) {
        if (null != input) {
            char[] c = input.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == 12288) {
                    c[i] = (char) 32;
                    continue;
                }
                if (c[i] > 65280 && c[i] < 65375)
                    c[i] = (char) (c[i] - 65248);
            }
            return new String(c);
        }
        return "";
    }

    /**
     * 设置View可见性
     *
     * @param v
     * @param isVisible
     */
    public static void setVisible(View v, boolean isVisible) {
        if (null != v) {
            if (isVisible) {
                if (v.getVisibility() != View.VISIBLE) {
                    v.setVisibility(View.VISIBLE);
                }
            } else {
                if (v.getVisibility() != View.GONE) {
                    v.setVisibility(View.GONE);
                }
            }
        }
    }


    /**
     * 设置View的invisible属性
     * 占用空间，但是不显示
     *
     * @param v
     * @param isInVisible
     */
    public static void setInVisible(View v, boolean isInVisible) {
        if (null != v) {
            if (isInVisible) {
                if (v.getVisibility() != View.INVISIBLE) {
                    v.setVisibility(View.INVISIBLE);
                }
            } else {
                if (v.getVisibility() != View.GONE) {
                    v.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 设置点击事件
     *
     * @param v
     * @param l
     */
    public static void setOnClickListener(View v, View.OnClickListener l) {
        if (null != v) {
            v.setOnClickListener(l);
        }
    }

    public static void SetGradleImg(View v) {
        if (null != v) {
            v.setVisibility(View.VISIBLE);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setVisibility(View.GONE);
                }
            });
        }
    }

    public static boolean NoNullAndEmpty(String s) {

        if (!TextUtils.isEmpty(s) && s != null) {
            return true;
        } else {
            return false;
        }

    }
}
