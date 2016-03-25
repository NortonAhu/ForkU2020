package com.bluecup.hongyu.mocku2020.ui.misc;

import android.text.SpannableStringBuilder;
import android.text.Spanned;

import java.util.ArrayDeque;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/24_下午4:57
 */
public class Truss {
    private final SpannableStringBuilder builder;
    private final ArrayDeque<Span> stack;

    public Truss() {
        builder = new SpannableStringBuilder();
        stack = new ArrayDeque<>();
    }

    public Truss append(String string) {
        builder.append(string);
        return this;
    }

    public Truss append(CharSequence charSequence) {
        builder.append(charSequence);
        return this;
    }

    public Truss append(int number) {
        builder.append(String.valueOf(number));
        return this;
    }

    public Truss append(char c) {
        builder.append(c);
        return this;
    }

    public Truss pushSpan(Object span) {
        stack.addLast(new Span(builder.length(), span));
        return this;
    }

    public Truss popSpan() {
        Span span = stack.getLast();
        builder.setSpan(span, span.start, builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return this;
    }

    public CharSequence build() {
        while (!stack.isEmpty()) {
            popSpan();
        }
        return builder;
    }


    private static final class Span {
        private final int start;
        private final Object span;

        public Span(int start, Object span) {
            this.start = start;
            this.span = span;
        }
    }
}
