package com.yasic.omousql.Model;

import com.yasic.omousql.Javabean.CallbackBean;

/**
 * Created by Yasic on 2016/5/20.
 */
public class TencentNewsModel implements ITopicModel {
    @Override
    public CallbackBean getNewsList(String url) {
        return  null;
    }

    @Override
    public CallbackBean getNewsDetail(String url) {
        return null;
    }
}
