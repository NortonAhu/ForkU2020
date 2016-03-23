package com.bluecup.hongyu.mocku2020.data;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.orhanobut.logger.Logger;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okio.BufferedSink;
import okio.Okio;
import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Des:
 * Created by hongyu
 * Date:16/3/22_下午7:39
 */
@Singleton
public final class LumberYard {
    public static final int BUFFER_SIZE = 200;
    private final Application app;

    private final Deque<Entry> entries = new ArrayDeque<>(BUFFER_SIZE + 1);


    public final PublishSubject<Entry> entrySubject = PublishSubject.create();

    @Inject
    public LumberYard(Application app) {
        this.app = app;
    }

    public ArrayList<Entry> bufferedLogs() {
        return new ArrayList<>(entries);
    }

    public Observable<Entry> logs() {
        return entrySubject;
    }

    public Observable<File> save() {
        return Observable.create(new Observable.OnSubscribe<File>() {

            @Override
            public void call(Subscriber<? super File> subscriber) {
                File folder = app.getExternalCacheDir();
                if (folder == null) {
                    subscriber.onError(new IOException("External storage is not mounted"));
                    return;
                }
                String fileName = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
                File outFile = new File(folder, fileName);
                Logger.e("logfile path is %s", outFile.getAbsoluteFile().toString());
                BufferedSink sink = null;
                try {
                    sink = Okio.buffer(Okio.sink(outFile));
                    List<Entry> entries = bufferedLogs();
                    for (Entry entry : entries) {
                        sink.writeUtf8(entry.prettyPrint()).writeByte('\n');
                    }
                    subscriber.onNext(outFile);
                    subscriber.onCompleted();
                }  catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                } finally {
                    if (sink != null) {
                        try {
                            sink.close();
                            sink = null;
                        } catch (IOException e) {
                            e.printStackTrace();
                            subscriber.onError(e);
                        }
                    }

                }

            }
        });
    }

    public void cleanUp() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                File logFolder = app.getExternalCacheDir();
                if (logFolder != null) {
                    for (File file:logFolder.listFiles()) {
                        if (file.getName().endsWith(".log")) {
                            file.delete();
                        }
                    }
                }
                return null;
            }
        }.execute();
    }

    public static final class Entry {
        public final int level;
        public final String tag;
        public final String message;

        public Entry(int level, String tag, String message) {
            this.level = level;
            this.tag = tag;
            this.message = message;
        }

        public String prettyPrint() {
            return String.format("%22s %s %s", tag, displayLevel(),
                    // Indent newlines to match the original indentation.
                    message.replaceAll("\\n", "\n                         "));
        }

        public String displayLevel() {
            switch (level) {
                case Log.VERBOSE:
                    return "V";
                case Log.DEBUG:
                    return "D";
                case Log.INFO:
                    return "I";
                case Log.WARN:
                    return "W";
                case Log.ERROR:
                    return "E";
                case Log.ASSERT:
                    return "A";
                default:
                    return "?";
            }
        }
    }
}
