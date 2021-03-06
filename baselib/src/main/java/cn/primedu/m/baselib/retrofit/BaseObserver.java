package cn.primedu.m.baselib.retrofit;

import cn.primedu.m.baselib.base.SwToast;
import cn.primedu.m.baselib.util.LogUtils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<BaseData<T>> {
    //mDisposable.dispose();开光，用来切断连接
    private Disposable mDisposable;
    private final int SUCCESS_CODE = -1;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(BaseData<T> value) {
        if (value.getCode() == SUCCESS_CODE) {
            T t = value.getData();
            onHandleSuccess(t);
        } else {
            onHandleError(value.getCode(), value.getErr_msg());
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.d("gesanri", "error:" + e.toString());
//        Toast.makeText(mContext, "网络异常，请稍后再试", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onComplete() {
//        Log.d("gesanri", "onComplete");
    }

    public abstract void onHandleSuccess(T t);

    //错误信息统一处理
    void onHandleError(int code, String message) {
        SwToast.show(message);
    }
}
