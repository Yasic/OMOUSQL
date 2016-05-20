package com.yasic.omousql.Presenter;

import android.support.v7.widget.Toolbar;

import com.yasic.omousql.Javabean.NewsBean;
import com.yasic.omousql.R;
import com.yasic.omousql.View.ContainerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Yasic on 2016/5/18.
 */
public class ContainerPresenter extends BasePresenterActivity<ContainerView> {
    @Override
    protected void onBindBVI() {
        setSupportActionBar((Toolbar) BVIView.getView().findViewById(R.id.tb_container));
        BVIView.setPresenter(this);
        List<String> tabTitleList = new ArrayList<>();
        Realm newsRealm = Realm.getInstance(new RealmConfiguration.Builder(getApplicationContext()).name("NewsInfo.realm").build());
        for (int i = 1; i < 4; i++){
            RealmResults<NewsBean> results = newsRealm.where(NewsBean.class)
                    .equalTo("title","新浪新闻" + i)
                    .findAll();
            if (results.size() == 0){
                newsRealm.beginTransaction();
                NewsBean target = newsRealm.createObject(NewsBean.class);
                target.setTitle("新浪新闻" + i);
                target.setDetail("新浪新闻" + i + "正文");
                target.setUrl("新浪");
                newsRealm.commitTransaction();
            }
            else {
            }
        }
        for (int i = 1; i < 4; i++){
            RealmResults<NewsBean> results = newsRealm.where(NewsBean.class)
                    .equalTo("title","腾讯新闻" + i)
                    .findAll();
            if (results.size() == 0){
                newsRealm.beginTransaction();
                NewsBean target = newsRealm.createObject(NewsBean.class);
                target.setTitle("腾讯新闻" + i);
                target.setDetail("腾讯新闻" + i + "正文");
                target.setUrl("腾讯");
                newsRealm.commitTransaction();
            }
            else {
            }
        }
        for (int i = 1; i < 4; i++){
            RealmResults<NewsBean> results = newsRealm.where(NewsBean.class)
                    .equalTo("title","网易新闻" + i)
                    .findAll();
            if (results.size() == 0){
                newsRealm.beginTransaction();
                NewsBean target = newsRealm.createObject(NewsBean.class);
                target.setTitle("网易新闻" + i);
                target.setDetail("网易新闻" + i + "正文");
                target.setUrl("网易");
                newsRealm.commitTransaction();
            }
            else {
            }
        }
        tabTitleList.add("新浪新闻");
        tabTitleList.add("腾讯新闻");
        tabTitleList.add("网易新闻");
        List<BasePresenterFragment> basePresenterFragmentList = new ArrayList<>();
        basePresenterFragmentList.add(new SinaNewsPresenter());
        basePresenterFragmentList.add(new TencentNewsPresenter());
        basePresenterFragmentList.add(new NeteaseNewsPresenter());
        BVIView.setViewPagerAndTablayout(tabTitleList, basePresenterFragmentList);
    }


    @Override
    protected Class<ContainerView> getBVIClass() {
        return ContainerView.class;
    }
}
