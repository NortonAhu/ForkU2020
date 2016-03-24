package com.bluecup.hongyu.mocku2020.ui.trending;

import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.TemporalUnit;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/24_上午11:47
 */
enum TrendingTimeSpan {
    DAY("today", 1, ChronoUnit.DAYS),
    WEEK("lastweek", 1, ChronoUnit.WEEKS),
    MONTH("lastmonth", 1, ChronoUnit.MONTHS);

    private final String name;
    private final int duration;
    private final TemporalUnit durationUnit;

    TrendingTimeSpan(String name, int duration, TemporalUnit durationUnit) {
        this.name = name;
        this.duration = duration;
        this.durationUnit = durationUnit;
    }

    public LocalDate createdSince() {
        return LocalDate.now().minus(duration, durationUnit);
    }

    @Override
    public String toString() {
        return name;
    }
}
