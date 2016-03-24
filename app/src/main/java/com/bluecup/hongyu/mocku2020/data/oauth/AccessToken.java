package com.bluecup.hongyu.mocku2020.data.oauth;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_上午11:56
 */
@Qualifier @Retention(RUNTIME)
public @interface AccessToken {
}
