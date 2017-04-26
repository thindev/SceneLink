package com.calenstudio.scenelink;

import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;

import android.support.v7.app.AppCompatDelegate;

import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;



import com.ashokvarma.bottomnavigation.BottomNavigationBar;


import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity {
static {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
}
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();
        mTextMessage = (TextView) findViewById(R.id.message);
        this.initBottomNavigationBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_main, menu);//加载menu文件到布局

        return true;
    }


    private  void initActionBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
    }

    private void initBottomNavigationBar()
    {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.bnv_link, R.string.title_link))
                .addItem(new BottomNavigationItem(R.drawable.bnv_find, R.string.title_find))
                .addItem(new BottomNavigationItem(R.drawable.bnv_resource, R.string.title_resource))
                .addItem(new BottomNavigationItem(R.drawable.bnv_my, R.string.title_my))
                .initialise();

    }

}
