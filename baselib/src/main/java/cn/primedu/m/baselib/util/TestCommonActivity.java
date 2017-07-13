package cn.primedu.m.baselib.util;

import android.support.v4.app.Fragment;

import cn.primedu.m.baselib.base.SWBaseActivity;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/5/27
 */
public class TestCommonActivity extends SWBaseActivity {

    @Override
    protected Fragment getFragment() {
        String fragmentName = getIntent().getStringExtra(Key.KEY_FRAGMENT_NAME);
        Fragment fragment = null;
        if (fragmentName != null) {
            fragment = Fragment.instantiate(this, fragmentName, getIntent().getExtras());
        }
        return fragment;
    }

}
