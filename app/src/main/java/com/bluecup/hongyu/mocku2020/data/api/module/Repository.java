package com.bluecup.hongyu.mocku2020.data.api.module;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Preconditions;

import org.threeten.bp.Instant;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午3:02
 */
public final class Repository {
    @NonNull
    public final String name;
    @NonNull public final User owner;
    @Nullable
    public final String description;

    public final long watchers;
    public final long forks;

    public final String html_url;

    public final Instant updated_at;

    public Repository(Builder builder) {
        this.name = Preconditions.checkNotNull(builder.name, "name == null");
        this.owner = Preconditions.checkNotNull(builder.owner, "owner == null");
        this.description = builder.description;
        this.watchers = builder.stars;
        this.forks = builder.forks;
        this.html_url = Preconditions.checkNotNull(builder.htmlUrl, "html_url == null");
        this.updated_at = Preconditions.checkNotNull(builder.updatedAt, "updated_at == null");
    }

    public static final class Builder {
        private String name;
        private User owner;
        private String description;
        private long stars;
        private long forks;
        private String htmlUrl;
        // TODO 这是干嘛的啊?
        private Instant updatedAt;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder owner(User owner) {
            this.owner = owner;
            return this;
        }

        private Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder stars(long stars) {
            this.stars = stars;
            return this;
        }

        public Builder forks(long forks) {
            this.forks = forks;
            return this;
        }

        public Builder htmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
            return this;
        }

        public Builder updatedAt(Instant updatedAt){
            this.updatedAt = updatedAt;
            return this;
        }

        public Repository build() {
            return new Repository(this);
        }
    }

}
