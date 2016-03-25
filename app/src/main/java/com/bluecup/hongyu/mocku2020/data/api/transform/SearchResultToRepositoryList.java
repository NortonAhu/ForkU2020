package com.bluecup.hongyu.mocku2020.data.api.transform;

import com.bluecup.hongyu.mocku2020.data.api.module.RepositoriesResponse;
import com.bluecup.hongyu.mocku2020.data.api.module.Repository;

import java.util.Collections;
import java.util.List;

import retrofit2.adapter.rxjava.Result;
import rx.functions.Func1;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/25_上午10:33
 */
public class SearchResultToRepositoryList implements Func1<Result<RepositoriesResponse>, List<Repository>> {
    private static volatile SearchResultToRepositoryList instance;

    public static SearchResultToRepositoryList instance() {
        if (instance == null) {
            instance = new SearchResultToRepositoryList();
        }
        return instance;
    }

    @Override
    public List<Repository> call(Result<RepositoriesResponse> result) {
        RepositoriesResponse repositoriesResponse = result.response().body();
        // TODO 需要了解下
        return repositoriesResponse.items == null ?
                Collections.<Repository>emptyList() : repositoriesResponse.items;

    }
}
