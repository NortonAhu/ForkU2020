package com.bluecup.hongyu.mocku2020.data.api.module;

import java.util.List;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午2:58
 */
public class RepositoriesResponse {
    public final List<Repository> items;

    public RepositoriesResponse(List<Repository> items) {
        this.items = items;
    }
}
