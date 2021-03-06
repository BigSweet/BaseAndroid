package cn.primedu.m.baselib.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

import cn.primedu.m.baselib.R;
import cn.primedu.m.baselib.util.CstTopBanner;
import cn.primedu.m.baselib.util.LogUtils;


/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/4/12
 */
public abstract class SWBaseActivity extends FragmentActivity {
    private SparseArray<View> mViews;
    private FragmentManager mFragmentManager;
    private Bundle resultBundle;
    protected RelativeLayout layout;
    private int resultCode;
    private SWBaseFragment currentFragment;
    private CstTopBanner mCstLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        mFragmentManager = getSupportFragmentManager();
        layout = (RelativeLayout) findViewById(R.id.base_activity_layout);
        mCstLayout = (CstTopBanner) findViewById(R.id.csttopbanner);
        //滑动返回
        mViews = new SparseArray<>();
//        InitView();

        if (savedInstanceState == null) {
            replace(getFragment());
        }

        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                currentFragment = getCurrentFragment();
                if (currentFragment != null && resultCode == RESULT_OK) {
                    currentFragment.onFragmentResult(resultCode, resultBundle);
                }
                resultCode = RESULT_CANCELED;
                resultBundle = null;
            }
        });
    }

    private CommonAdapter adapter;

    //替换Fragment（View层）
    public void replace(final Fragment fragment) {
        replace(fragment, null);
    }

    public void replace(final Fragment fragment, String tag) {
        replace(fragment, tag, false);
    }


    public void replace(final Fragment fragment, String tag, boolean canBack) {
        if (null == fragment) {
            return;
        }
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        //暂时不写动画，需要的时候在写
//        ft.setCustomAnimations(R.anim.right_in, R.anim.left_out, R.anim.left_in, R.anim.right_out);
        if (TextUtils.isEmpty(tag)) {
            tag = UUID.randomUUID().toString();
        }
        ft.replace(R.id.base_activity_replace, fragment, tag);
        if (canBack) {
            ft.addToBackStack(tag);
        }
        ft.commitAllowingStateLoss();
    }

    protected abstract Fragment getFragment();

    public SWBaseFragment getCurrentFragment() {
        Fragment fragment = mFragmentManager.findFragmentById(R.id.base_activity_replace);
        if (fragment != null && fragment instanceof SWBaseFragment) {
            return (SWBaseFragment) fragment;
        }
        return null;
    }

    //改变背景色
    public void changeLayoutBg(int color) {
        if (null != layout) {
            layout.setBackgroundColor(getResources().getColor(color));
        }
    }

    public void removeTopBanner() {
        if (null != layout) {
            if (null != mCstLayout) {
                layout.removeView(mCstLayout);
            }
        }
    }


    public void removeBackGroud() {
        if (layout != null) {
            layout.setBackgroundResource(R.color.white);
        }
    }


    //滑动返回
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    protected boolean isCanBackToast() {
        return false;
    }

    private long exitTime;

    protected void showBackWaringToast() {
        Toast.makeText(this, "再按一次退出", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        if (isCanBackToast()) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                showBackWaringToast();
                exitTime = System.currentTimeMillis();
            } else {
                superOnBackPressed();
                System.exit(0);
            }
        } else {
            currentFragment = getCurrentFragment();
            if (currentFragment != null && currentFragment.onFragmentBackPressd()) {
                return;
            }
            superOnBackPressed();
        }
    }

    public void superOnBackPressed() {
        LogUtils.d("BaseActivity", "superOnBackPressed:" + resultCode + "---" + mFragmentManager.getBackStackEntryCount());
        if (mFragmentManager.getBackStackEntryCount() == 0 && resultCode != RESULT_CANCELED) {
            Intent data = null;
            if (resultBundle != null) {
                data = new Intent();
                data.putExtras(resultBundle);
            }
            setResult(resultCode, data);
        }
        super.onBackPressed();
    }

    int requestcode;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (mFragmentManager != null) {
            List<Fragment> fragments = mFragmentManager.getFragments();
            if (fragments != null && fragments.size() > 0) {
                for (Fragment fragment : fragments) {
                    if (fragment != null && fragment.isAdded() && fragment.isVisible() && fragment.getUserVisibleHint()) {
                        fragment.onActivityResult(requestCode, resultCode, data);
                    }
                }
            }
        }
    }

    //直接findviewbyid在基类里面做了
    public <V extends View> V findView(int viewId) {
        V view = (V) mViews.get(viewId);
        if (view == null) {
            view = (V) findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }


    //在这里初始化数据
//    protected abstract void InitView();

    public int getLayoutId() {
        return R.layout.base_activity;
    }


    public CstTopBanner getCstTopBanner() {
        return mCstLayout;
    }

    public void setFragmentResult(int resultCode, Bundle resultBundle) {
        this.resultCode = resultCode;
        this.resultBundle = resultBundle;
    }

    public void showFragment(int contentID, SWBaseFragment toFragment) {
        showFragment(contentID, toFragment, false);
    }

    public void showFragment(int contentID, SWBaseFragment toFragment, boolean isAdd2Back) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }
        String tag = toFragment.getClass().getSimpleName();
        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment == null) {
            ft.add(contentID, toFragment, tag);
        } else {
            ft.show(toFragment);
        }
        if (isAdd2Back) {
            ft.addToBackStack(tag);
        }
//        ft.commit();
        ft.commitAllowingStateLoss();
        currentFragment = toFragment;
    }


    public RelativeLayout getLayout() {
        return layout;
    }

}
