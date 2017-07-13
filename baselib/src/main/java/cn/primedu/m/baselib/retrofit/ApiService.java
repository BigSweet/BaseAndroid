package cn.primedu.m.baselib.retrofit;

import cn.primedu.m.baselib.model.TestBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 介绍：所有的接口都写到这里
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/4/19
 */
public interface ApiService {
    //获取token
    @GET("aggre/user/1311139/follow/feed/list?appid=1&appplt=aph&token=c42832d72cdb08f39d2d4c930327ecf7&appver=3.1.0&pageSize=20")
    Observable<BaseData<TestBean>> getToken(@Query("nt") String nt);
}
