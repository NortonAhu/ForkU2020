package com.bluecup.hongyu.mocku2020.ui.trending;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bluecup.hongyu.mocku2020.R;
import com.bluecup.hongyu.mocku2020.data.Funcs;
import com.bluecup.hongyu.mocku2020.data.Injector;
import com.bluecup.hongyu.mocku2020.data.IntentFactory;
import com.bluecup.hongyu.mocku2020.data.api.GithubService;
import com.bluecup.hongyu.mocku2020.data.api.Order;
import com.bluecup.hongyu.mocku2020.data.api.Results;
import com.bluecup.hongyu.mocku2020.data.api.SearchQuery;
import com.bluecup.hongyu.mocku2020.data.api.module.RepositoriesResponse;
import com.bluecup.hongyu.mocku2020.data.api.module.Repository;
import com.bluecup.hongyu.mocku2020.data.api.module.Sort;
import com.bluecup.hongyu.mocku2020.data.api.transform.SearchResultToRepositoryList;
import com.bluecup.hongyu.mocku2020.ui.misc.BetterViewAnimator;
import com.bluecup.hongyu.mocku2020.ui.misc.DividerItemDecoration;
import com.bluecup.hongyu.mocku2020.util.Intents;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import retrofit2.adapter.rxjava.Result;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午4:16
 */
public class TrendingView extends LinearLayout implements SwipeRefreshLayout.OnRefreshListener
        , TrendingAdapter.RepositoryClickListener {
    private final PublishSubject<TrendingTimeSpan> timeSpanSubject;
    private final TrendingTimeSpanAdapter timeSpanAdapter;
    private final TrendingAdapter trendingAdapter;
    @Bind(R.id.trending_loading_message)
    TextView loadingMessageTxt;
    @Bind(R.id.trending_toolbar)
    Toolbar toolbarView;
    @Bind(R.id.trending_timespan)
    Spinner timeSpanView;
    @Bind(R.id.trending_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.trending_animator)
    BetterViewAnimator animatorView;
    @Bind(R.id.trending_list)
    RecyclerView trendingView;
    @Inject
    Picasso picasso;
    @Inject
    DrawerLayout drawerLayout;
    @Inject
    GithubService githubService;
    @Inject
    IntentFactory intentFactory;
    @BindDimen(R.dimen.trending_divider_padding_start)
    float dividerPaddingStart;

    private final CompositeSubscription subscription = new CompositeSubscription();
    private Action1< Result<RepositoriesResponse>> trendingerror = new Action1<Result<RepositoriesResponse>>() {
        @Override
        public void call(Result<RepositoriesResponse> result) {
            if (result.isError()) {
                Logger.e(result.error(), "Fail to get trending reposity");
            } else {
                retrofit2.Response<RepositoriesResponse> response = result.response();
            }
            swipeRefreshLayout.setRefreshing(false);
            animatorView.setDisplayedChild(R.id.trending_error);

        }
    };

    public TrendingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            Injector.obtain(context).inject(this);
        }
        timeSpanSubject = PublishSubject.create();
        timeSpanAdapter = new TrendingTimeSpanAdapter(new ContextThemeWrapper(getContext(),
                R.style.Theme_U2020_TrendingTimespan));
        trendingAdapter = new TrendingAdapter(picasso, this);

    }

    private Func1<TrendingTimeSpan, rx.Observable<Result<RepositoriesResponse>>> trendingSearch =
            new Func1<TrendingTimeSpan, rx.Observable<Result<RepositoriesResponse>>>() {
                @Override
                public rx.Observable<Result<RepositoriesResponse>> call(TrendingTimeSpan trendingTimeSpan) {
                    SearchQuery trendingQuery = new SearchQuery.Builder()
                            .createdSince(trendingTimeSpan.createdSince()).builder();

                    return githubService.repositories(trendingQuery, Sort.STARS, Order.DESC)
                            .subscribeOn(Schedulers.io());
                }
            };

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        AnimationDrawable ellipsis = (AnimationDrawable) ContextCompat.getDrawable(getContext(), R.drawable.dancing_ellipsis);
        loadingMessageTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, ellipsis, null);
        ellipsis.start();

        toolbarView.setNavigationIcon(R.drawable.menu_icon);
        toolbarView.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        timeSpanView.setAdapter(timeSpanAdapter);
        timeSpanView.setSelection(TrendingTimeSpan.WEEK.ordinal());

        swipeRefreshLayout.setColorSchemeColors(R.color.accent);
        swipeRefreshLayout.setOnRefreshListener(this);

        trendingAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                animatorView.setDisplayedChild(trendingAdapter.getItemCount() == 0
                        ? R.id.trending_empty : R.id.trending_swipe_refresh);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        trendingView.setLayoutManager(new LinearLayoutManager(getContext()));
        trendingView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST, dividerPaddingStart, safeIsRtl()));
        trendingView.setAdapter(trendingAdapter);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        rx.Observable<Result<RepositoriesResponse>> result = timeSpanSubject
                        .flatMap(trendingSearch)
                        .observeOn(AndroidSchedulers.mainThread())
                        .share(); // TODO 不懂

        subscription.add(result.filter(Results.isSuccessful())
                             .map(SearchResultToRepositoryList.instance())
                             .subscribe(trendingAdapter));
        subscription.add(result.filter(Funcs.not(Results.isSuccessful())).subscribe(trendingerror));
        onRefresh();
    }

    private boolean safeIsRtl() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1 && isRtl();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private boolean isRtl() {
        return getLayoutDirection() == LAYOUT_DIRECTION_RTL;
    }

    @Override
    public void onRefresh() {
        timeSpanSelected(timeSpanView.getSelectedItemPosition());
    }

    @OnItemSelected(R.id.trending_timespan)
    public void timeSpanSelected(final int selectedItemPosition) {
        if (animatorView.getDisplayedChildId() != R.id.trending_swipe_refresh) {
            animatorView.setDisplayedChild(R.id.trending_loading);
        }
        post(()->{
            swipeRefreshLayout.setRefreshing(true);
            timeSpanSubject.onNext(timeSpanAdapter.getItem(selectedItemPosition));
        });
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        subscription.unsubscribe();
    }

    @Override
    public void onRepositoryClick(Repository repository) {
        Intents.maybeStartActivity(getContext(), intentFactory.createUrlIntent(repository.html_url));
    }
}
