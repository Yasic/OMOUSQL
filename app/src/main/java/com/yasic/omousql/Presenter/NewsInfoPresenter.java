package com.yasic.omousql.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.yasic.omousql.Javabean.NewsBean;
import com.yasic.omousql.Model.NeteaseNewsModel;
import com.yasic.omousql.Model.SinaNewsModel;
import com.yasic.omousql.Model.TencentNewsModel;
import com.yasic.omousql.View.NewsInfoView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Yasic on 2016/5/19.
 */
public class NewsInfoPresenter extends BasePresenterActivity<NewsInfoView> {
    SinaNewsModel sinaNewsModel = new SinaNewsModel();
    TencentNewsModel tencentNewsModel = new TencentNewsModel();
    NeteaseNewsModel neteaseNewsModel = new NeteaseNewsModel();
    @Override
    protected void onBindBVI() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String url = bundle.getString("URL");
        String title = bundle.getString("TITLE");
        String type = bundle.getString("TYPE");
        BVIView.setPresenter(this);
        BVIView.setTitle(title);
        switch (type){
            case "1":
                getSinaNewsInfo(title);
                break;
            case "2":
                getTencentNewsInfo(title);
                break;
            case "3":
                getNetEaseNewsInfo(title);
                break;
        }
    }

    private void getNetEaseNewsInfo(final String title) {
        Observable.create(new Observable.OnSubscribe<RealmResults<NewsBean>>() {
            @Override
            public void call(Subscriber<? super RealmResults<NewsBean>> subscriber) {
                Realm newsRealm = Realm.getInstance(new RealmConfiguration.Builder(getApplicationContext()).name("NewsInfo.realm").build());
                RealmResults<NewsBean> results = newsRealm.where(NewsBean.class)
                        .equalTo("title",title)
                        .findAll();
                if (results.size() == 0){
                }
                else {
                    subscriber.onNext(results);
                    subscriber.onCompleted();
                }
            }
        })
                .subscribe(new Action1<RealmResults<NewsBean>>() {
                    @Override
                    public void call(RealmResults<NewsBean> callbackBean) {
                        BVIView.setInfo(callbackBean.get(0).getDetail());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.i("fuckerror", throwable.getMessage());
                    }
                });
    }

    private void getTencentNewsInfo(final String title) {
        Observable.create(new Observable.OnSubscribe<RealmResults<NewsBean>>() {
            @Override
            public void call(Subscriber<? super RealmResults<NewsBean>> subscriber) {
                Realm newsRealm = Realm.getInstance(new RealmConfiguration.Builder(getApplicationContext()).name("NewsInfo.realm").build());
                RealmResults<NewsBean> results = newsRealm.where(NewsBean.class)
                        .equalTo("title",title)
                        .findAll();
                if (results.size() == 0){
                }
                else {
                    subscriber.onNext(results);
                    subscriber.onCompleted();
                }
            }
        })
                .subscribe(new Action1<RealmResults<NewsBean>>() {
                    @Override
                    public void call(RealmResults<NewsBean> callbackBean) {
                        BVIView.setInfo(callbackBean.get(0).getDetail());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.i("fuckerror", throwable.getMessage());
                    }
                });
    }

    public void getSinaNewsInfo(final String title) {
        Observable.create(new Observable.OnSubscribe<RealmResults<NewsBean>>() {
            @Override
            public void call(Subscriber<? super RealmResults<NewsBean>> subscriber) {
                Realm newsRealm = Realm.getInstance(new RealmConfiguration.Builder(getApplicationContext()).name("NewsInfo.realm").build());
                RealmResults<NewsBean> results = newsRealm.where(NewsBean.class)
                        .equalTo("title",title)
                        .findAll();
                if (results.size() == 0){
                }
                else {
                    subscriber.onNext(results);
                    subscriber.onCompleted();
                }
            }
        })
                .subscribe(new Action1<RealmResults<NewsBean>>() {
                    @Override
                    public void call(RealmResults<NewsBean> callbackBean) {
                        BVIView.setInfo(callbackBean.get(0).getDetail());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.i("fuckerror", throwable.getMessage());
                    }
                });
    }

    @Override
    protected Class<NewsInfoView> getBVIClass() {
        return NewsInfoView.class;
    }
}
