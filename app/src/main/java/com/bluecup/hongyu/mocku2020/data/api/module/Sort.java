package com.bluecup.hongyu.mocku2020.data.api.module;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午3:55
 */
public enum Sort {
    STARS("watches"),
    FORKS("forks"),
    UPDATED("updated");

    private final String value;

    Sort(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
