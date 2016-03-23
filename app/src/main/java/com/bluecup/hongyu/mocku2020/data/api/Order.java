package com.bluecup.hongyu.mocku2020.data.api;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午3:57
 */
public enum Order {
    ASC("asc"),
    DESC("desc");

    private final String value;

    Order(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
