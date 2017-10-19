package cn.primedu.m.baseandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.primedu.m.baselib.base.SWBaseFragment;
import cn.primedu.m.baselib.base.SwToast;
import cn.primedu.m.baselib.base.UrlAddress;
import cn.primedu.m.baselib.model.ListBean;
import cn.primedu.m.baselib.model.TestBean;
import cn.primedu.m.baselib.retrofit.BaseObserver;
import cn.primedu.m.baselib.retrofit.RetrofitManager;
import cn.primedu.m.baselib.util.CustomLoadMoreView;
import cn.primedu.m.baselib.util.LogUtils;
import cn.primedu.m.baselib.util.NoNullUtils;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/7/13
 */
public class MainFragment extends SWBaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    //    SimpleDraweeView mSimpleDraweeView;
    RecyclerView mRecyclerView;
    List<ListBean> data = new ArrayList<>();
    BaseQuickAdapter mBaseQuickAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String nt;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRecyclerView = findViewById(R.id.recycler_test);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refersh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        topBanner.setLeft(null, "左边", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwToast.show("左边被点击啦");
            }
        });

        topBanner.setCentre(null, "中间", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwToast.show("中间");
            }
        });
        topBanner.setRight(null, "右边", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwToast.show("右边");
            }
        });
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mBaseQuickAdapter = new BaseQuickAdapter<ListBean, BaseViewHolder>(R.layout.rv_item, data) {

            @Override
            protected void convert(BaseViewHolder helper, ListBean item) {
                NoNullUtils.setText((TextView) helper.getView(R.id.tv_test)
                        , item.getUser().getName());
                SimpleDraweeView simpleDraweeView = helper.getView(R.id.main_course_item_siv);
//                simpleDraweeView.setImageURI();
            }

        });
        mBaseQuickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        View view = View.inflate(getActivity(), R.layout.test_head, null);
//        View view2 = View.inflate(getActivity(), R.layout.test_head, null);
//        mBaseQuickAdapter.addHeaderView(view);
//        mBaseQuickAdapter.addFooterView(view2);
        mBaseQuickAdapter.setEnableLoadMore(true);
        mBaseQuickAdapter.setLoadMoreView(new CustomLoadMoreView());
        mBaseQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                RetrofitManager.getInstance().putParams("nt", nt).getTest(UrlAddress.getTest(),TestBean.class).subscribe(new BaseObserver<TestBean>() {
                    @Override
                    public void onHandleSuccess(TestBean testBeen) {
                        data = testBeen.getlist();
                        nt = testBeen.getNt();
                        LogUtils.d(nt);
                        mBaseQuickAdapter.loadMoreComplete();
                        //数据全部加载完毕
//                        mBaseQuickAdapter.loadMoreEnd();
                        mBaseQuickAdapter.addData(data);
                    }
                });
            }
        });
        loadData();
//        mSimpleDraweeView = findViewById(R.id.user_adv);
//        mSimpleDraweeView.setImageURI("http://orxzn1n7o.bkt.clouddn.com/%E5%85%AB%E5%AD%97%E8%88%9E.jpg");
//        FrescoUtils.loadUrl("http://orxzn1n7o.bkt.clouddn.com/%E5%85%AB%E5%AD%97%E8%88%9E.jpg", mSimpleDraweeView);
  /*      ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });*/
    }


    public void loadData() {
        RetrofitManager.getInstance().putParams("nt", "").getTest(UrlAddress.getTest(),TestBean.class).subscribe(new BaseObserver<TestBean>() {
            @Override
            public void onHandleSuccess(TestBean testBeen) {
                data = testBeen.getlist();
                nt = testBeen.getNt();
                LogUtils.d(nt);
                mBaseQuickAdapter.addData(data);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onRefresh() {
        RetrofitManager.getInstance().putParams("nt", "").getTest(UrlAddress.getTest(),TestBean.class).subscribe(new BaseObserver<TestBean>() {
            @Override
            public void onHandleSuccess(TestBean testBeen) {
                data = testBeen.getlist();
                nt = testBeen.getNt();
                LogUtils.d(nt);
                mBaseQuickAdapter.replaceData(data);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }
}
