package com.bluecup.hongyu.mocku2020.data;

import rx.functions.Func1;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/25_上午11:09
 */
public final class Funcs {
    public static <T> Func1<T, Boolean> not(final Func1<T, Boolean> func) {
        return value -> !func.call(value);
    }

    public Funcs() {
        throw new AssertionError("no instance");
    }
}
