package com.coderchang.kaywuzhihu.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.coderchang.kaywuzhihu.fragment.MainFragment;
import com.coderchang.kaywuzhihu.R;
import com.coderchang.kaywuzhihu.fragment.Fragment1;
import com.coderchang.kaywuzhihu.fragment.WeatherFragment;
import com.coderchang.kaywuzhihu.fragment.Fragment3;
import com.coderchang.kaywuzhihu.utility.Utility;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private boolean isNetAvailable;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        isNetAvailable = Utility.NetWorkIsConnected(this);

        initView();


        setNavigationItem();

        if (!isNetAvailable) {
            Toast.makeText(this, "网络未打开，请检查网络", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MainFragment()).commit();
    }


    private void setNavigationItem() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_one:

                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MainFragment()).commit();
                        toolbar.setTitle("首页");
                        progressBar.setVisibility(View.GONE);
                        break;

                    case R.id.item_two:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new WeatherFragment()).commit();
                        toolbar.setTitle("天气");
                        progressBar.setVisibility(View.GONE);
                        break;
                    case R.id.item_three:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Fragment3()).commit();
                        toolbar.setTitle("我的动态");
                        progressBar.setVisibility(View.GONE);
                        break;
                    case R.id.item_four:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Fragment3()).commit();
                        toolbar.setTitle("附近的人");
                        progressBar.setVisibility(View.GONE);
                        break;

                    default:
                        break;

                }
                item.setChecked(true);
                drawerLayout.closeDrawers();//关闭抽屉
                return true;
            }
        });
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(Utility.getTime());
    }


    private void initView() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
