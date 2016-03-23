package com.bluecup.hongyu.mocku2020.data;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.threeten.bp.Instant;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午1:54
 */
public final class InstantAdapter {
    @ToJson
    public String toJson(Instant instant) {
        return instant.toString();
    }

    @FromJson
    public Instant fromJson(String str) {
        return Instant.parse(str);
    }
}
