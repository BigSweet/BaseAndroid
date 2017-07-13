package cn.primedu.m.baseandroid;

import android.support.v4.app.Fragment;

import cn.primedu.m.baselib.base.SWBaseActivity;

public class MainActivity extends SWBaseActivity {

    @Override
    protected Fragment getFragment() {
        return Fragment.instantiate(this, MainFragment.class.getName(), getIntent().getExtras());
    }
}
