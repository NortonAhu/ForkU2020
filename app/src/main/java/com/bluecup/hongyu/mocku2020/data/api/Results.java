package com.bluecup.hongyu.mocku2020.data.api;

import retrofit2.adapter.rxjava.Result;
import rx.functions.Func1;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午3:50
 */
public final class Results {
    public static Func1<Result<?>, Boolean> SUCCESSFUL =
            result -> !result.isError() && result.response().isSuccessful();

    public static Func1<Result<?>, Boolean> isSuccessful() {
        return SUCCESSFUL;
    }
    public Results() {
        throw new AssertionError("No instances.");
    }
}
