package com.bluecup.hongyu.mocku2020.data.api.module;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午3:04
 */
public class User {
    private final String login;
    private final String avator_url;

    public User(String login, @NonNull String avator_url) {
        this.login = Preconditions.checkNotNull(login, "login == null");
        this.avator_url = avator_url;
    }

    @Override
    public String toString() {
        return "User{" + "login='" + login + "\'"
                + ",avator_url='" + avator_url + '\'' + "}";
    }
}
