package com.yasic.omousql.Model;
import com.yasic.omousql.Javabean.CallbackBean;

/**
 * Created by Yasic on 2016/5/18.
 */
public interface ITopicModel {
    CallbackBean getNewsList(String url);
    CallbackBean getNewsDetail(String url);
}
