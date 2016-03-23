package com.bluecup.hongyu.mocku2020.data.api;

import com.bluecup.hongyu.mocku2020.data.api.module.Repository;
import com.bluecup.hongyu.mocku2020.data.api.module.Sort;

import java.util.List;

import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午2:54
 */
public interface GithubService {
    @GET("search/repositories")
    Observable<Result<List<Repository>>> repositories(//
                                                      @Query("q") SearchQuery query,
                                                      @Query("sort") Sort sort,
                                                      @Query("order") Order order
    );
}
