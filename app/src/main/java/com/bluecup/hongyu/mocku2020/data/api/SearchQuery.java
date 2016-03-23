package com.bluecup.hongyu.mocku2020.data.api;

import com.google.common.base.Preconditions;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/23_下午3:34
 */
public final class SearchQuery {
    private LocalDate createdSince;

    public SearchQuery(SearchQuery.Builder builder) {
        this.createdSince = Preconditions.checkNotNull(builder.createdSince, "createdSince == null");
    }

    @Override
    public String toString() {
        return createdSince == null ? null : "created:>=" + DateTimeFormatter.ISO_LOCAL_DATE.format(createdSince);
    }

    public static final class Builder {
        LocalDate createdSince;

        public Builder createdSince(LocalDate createdSince) {
            this.createdSince = createdSince;
            return this;
        }

        public SearchQuery builder() {
            return new SearchQuery(this);
        }

    }
}
