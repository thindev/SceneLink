package com.calenstudio.scenelink;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.app.AppCompatDelegate;

import android.support.v7.widget.Toolbar;

import android.view.Menu;

import android.view.MenuItem;
import android.widget.TextView;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;


import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.calenstudio.scenelink.view.mainpage.FindFragment;
import com.calenstudio.scenelink.view.mainpage.LinkFragment;
import com.calenstudio.scenelink.view.mainpage.MyFragment;
import com.calenstudio.scenelink.view.mainpage.ResourcesFragment;


import com.calenstudio.scenelink.view.mainpage.SearchLinkedSceneActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements FindFragment.OnFragmentInteractionListener
        ,LinkFragment.OnFragmentInteractionListener,
        MyFragment.OnFragmentInteractionListener,
        ResourcesFragment.OnFragmentInteractionListener

{
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    private Toolbar mActionBar;
    private ArrayList<Fragment> mFragments;
    private BottomNavigationBar mBottomNavigationBar;
    private int mLastSelectPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();
        this.initBottomNavigationBar();
        setActionBarTitle(0);
        selectContentView(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_main, menu);//加载menu文件到布局
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.options_item_scan:
                return true;
            case R.id.options_item_search:
                Intent searchIntent=new Intent(this, SearchLinkedSceneActivity.class);
                this.startActivity(searchIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initActionBar() {
        mActionBar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mActionBar);

    }

    private  void  initFragments()
    {
        mFragments=new ArrayList<>();
        mFragments.add(LinkFragment.newInstance("",""));
        mFragments.add(FindFragment.newInstance("",""));
        mFragments.add(ResourcesFragment.newInstance("",""));
        mFragments.add(ResourcesFragment.newInstance("",""));
    }
    private void initBottomNavigationBar() {
        if(mFragments==null){
            this.initFragments();
        }
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.bnv_link, R.string.title_link))
                .addItem(new BottomNavigationItem(R.drawable.bnv_find, R.string.title_find))
                .addItem(new BottomNavigationItem(R.drawable.bnv_resource, R.string.title_resource))
                .addItem(new BottomNavigationItem(R.drawable.bnv_my, R.string.title_my))
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                setActionBarTitle(position);
                selectContentView(position);
                mLastSelectPosition=position;
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
                setActionBarTitle(position);
            }
        });
    }
    private void setActionBarTitle(int position) {
        switch (position) {
            case 0:
                mActionBar.setTitle(R.string.title_link);
                break;
            case 1:
                mActionBar.setTitle(R.string.title_find);
                break;
            case 2:
                mActionBar.setTitle(R.string.title_resource);
                break;
            case 3:
                mActionBar.setTitle(R.string.title_my);
                break;
            default:
                mActionBar.setTitle(R.string.app_name);
        }
    }
    private  void  selectContentView(int position) {
        if (position >= 0 && mFragments != null && mFragments.size() > position) {
            FragmentTransaction ft= this.getSupportFragmentManager()
                    .beginTransaction();
            if(mLastSelectPosition>position)
            {
                ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
            }
            else
            {
                 ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            }


                    ft.replace(R.id.content,mFragments.get(position))
                    .commit();

        }
    }


}
