package com.bluecup.hongyu.mocku2020.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bluecup.hongyu.mocku2020.R;
import com.bluecup.hongyu.mocku2020.data.Injector;
import com.bluecup.hongyu.mocku2020.data.oauth.OauthService;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import dagger.ObjectGraph;

public class MainActivity extends Activity {

    @Bind(R.id.main_drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.main_content)
    FrameLayout content;
    @Bind(R.id.main_navigation)
    NavigationView drawer;
    @BindColor(R.color.status_bar)
    int statusColor;

    @Inject
    ViewContainer viewContainer;
    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColor(getWindow());
        }

        ObjectGraph objectGraph = Injector.obtain(getApplicationContext());
        objectGraph.inject(this);
        activityGraph = objectGraph.plus(new MainActivityModule(this));
        ViewGroup viewGroup = viewContainer.forActivity(this);

        inflater.inflate(R.layout.activity_main, viewGroup);
        ButterKnife.bind(this);

        drawerLayout.setStatusBarBackgroundColor(statusColor);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawer.setNavigationItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.nav_search:
                            Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                            break;
                        case R.id.nav_trending:
                            Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                            break;
                        default:
                            throw new IllegalStateException("unknown navigation item:" + item.getTitle());
                    }
                    drawerLayout.closeDrawers();
                    return true;
                }
        );
        inflater.inflate(R.layout.trending_view, content);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBarColor(Window window) {
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    public Object getSystemService(String name) {
        if (Injector.matchesService(name)) {
            return activityGraph;
        }
        return super.getSystemService(name);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Uri data = intent.getData();
        if (data == null) return;

        if ("u2020".equals(data.getScheme())) {
            Intent serviceIntent = new Intent(this, OauthService.class);
            serviceIntent.setData(data);
            startService(serviceIntent);
        }
    }

    @Override
    protected void onDestroy() {
        activityGraph = null;
        super.onDestroy();
    }
}
