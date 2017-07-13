package cn.primedu.m.baselib.model;

import java.util.List;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/7/13
 */
public class TestBean {
/*    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }*/
      private List<ListBean> list;
    private  String nt;

    public String getNt() {
        return nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    public List<ListBean> getlist() {
        return list;
    }

    public void setlist(List<ListBean> ListBean) {
        list = ListBean;
    }
}
