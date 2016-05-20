package com.yasic.omousql.Presenter;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;

import com.yasic.omousql.Javabean.NewsBean;
import com.yasic.omousql.Model.SinaNewsModel;
import com.yasic.omousql.View.SinaNewsView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Yasic on 2016/5/19.
 */
public class SinaNewsPresenter extends BasePresenterFragment<SinaNewsView> {
    private SinaNewsModel sinaNewsModel = new SinaNewsModel();

    @Override
    protected void onBindBVI() {
        BVIView.setPresenter(this);
        BVIView.initRvSinaNews(getActivity().getApplication());
        sinaNewsModel.getNewsList("http://news.sina.com.cn/china/");
        BVIView.getView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                BVIView.getView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //BVIView.setSwipeRefreshLayoutRefreshing(true);
            }
        });
        getNews();
    }

    public void hideFootbar(final View view) {
        ViewPropertyAnimator animator = view.animate().
                translationY(view.getHeight()).
                setInterpolator(new FastOutSlowInInterpolator()).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                showFootbar(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }


    public void showFootbar(final View view) {
        ViewPropertyAnimator animator = view.animate().
                translationY(0).
                setInterpolator(new FastOutSlowInInterpolator()).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {
                hideFootbar(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();

    }

    public void startNewsInfoPresenter(NewsBean newsBean){
        Intent intent = new Intent(getActivity(), NewsInfoPresenter.class);
        Bundle bundle = new Bundle();
        bundle.putString("URL", newsBean.getUrl());
        bundle.putString("TITLE", newsBean.getTitle());
        bundle.putString("TYPE", "1");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void getNews() {
        BVIView.setSwipeRefreshLayoutRefreshing(true);
        Observable.create(new Observable.OnSubscribe<RealmResults<NewsBean>>() {
            @Override
            public void call(Subscriber<? super RealmResults<NewsBean>> subscriber) {
                Realm newsRealm = Realm.getInstance(new RealmConfiguration.Builder(getActivity()).name("NewsInfo.realm").build());
                RealmResults<NewsBean> results = newsRealm.where(NewsBean.class)
                        .equalTo("url","新浪")
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
                        BVIView.setSwipeRefreshLayoutRefreshing(false);
                        List<NewsBean> newsBeanList = new ArrayList<>();
                        for (NewsBean newsBean : callbackBean){
                            newsBeanList.add(new NewsBean(newsBean.getTitle(), newsBean.getUrl(), newsBean.getDetail()));
                        }
                        BVIView.setRvNewsList(getActivity(), newsBeanList);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        try{
                            BVIView.setSwipeRefreshLayoutRefreshing(false);
                        }catch (Exception e){
                        }
                        Log.i("fuckerror", throwable.getMessage());
                    }
                });
    }

    @Override
    protected Class<SinaNewsView> getBVIClass() {
        return SinaNewsView.class;
    }
}
