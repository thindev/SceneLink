package com.calenstudio.scenelink;

import android.content.Intent;

import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.app.AppCompatDelegate;

import android.support.v7.widget.Toolbar;

import android.view.Menu;

import android.view.MenuItem;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;


import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import com.calenstudio.scenelink.view.mainpage.ClassifiedScenesFragment;
import com.calenstudio.scenelink.view.mainpage.DiscoverFragment;
import com.calenstudio.scenelink.view.mainpage.LinkFragment;
import com.calenstudio.scenelink.view.mainpage.MyFragment;
import com.calenstudio.scenelink.view.mainpage.NearbyFragment;


import com.calenstudio.scenelink.view.mainpage.RecommendFragment;
import com.calenstudio.scenelink.view.mainpage.SearchLinkedSceneActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements DiscoverFragment.OnFragmentInteractionListener,
        LinkFragment.OnFragmentInteractionListener,
        MyFragment.OnFragmentInteractionListener,
        NearbyFragment.OnFragmentInteractionListener,
        ClassifiedScenesFragment.OnFragmentInteractionListener,
        RecommendFragment.OnFragmentInteractionListener
{
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    private Toolbar mActionBar;
    private ArrayList<Fragment> mFragments;
    private Fragment mCurrentFragment;
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
        mFragments.add(DiscoverFragment.newInstance("",""));
        mFragments.add(NearbyFragment.newInstance("",""));
        mFragments.add(NearbyFragment.newInstance("",""));
        FragmentTransaction ft= this.getSupportFragmentManager()
                .beginTransaction();
        ft.add(R.id.content, mFragments.get(3),"3").hide(mFragments.get(3))
                .add(R.id.content,mFragments.get(2),"2").hide(mFragments.get(2))
                .add(R.id.content,mFragments.get(1),"1").hide(mFragments.get(1))
                .add(R.id.content,mFragments.get(0),"0").hide(mFragments.get(0));
        ft.show(mFragments.get(0));
        ft.commit();
        mCurrentFragment=mFragments.get(0);
    }
    private void initBottomNavigationBar() {
        if(mFragments==null){
            this.initFragments();
        }
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.bnv_link, R.string.title_link))
                .addItem(new BottomNavigationItem(R.drawable.bnv_find, R.string.title_discover))
                .addItem(new BottomNavigationItem(R.drawable.bnv_nearby, R.string.title_nearby))
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
                mActionBar.setTitle(R.string.title_discover);
                break;
            case 2:
                mActionBar.setTitle(R.string.title_nearby);
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
                ft.setCustomAnimations(R.anim.slide_in_left_scene_snapshot, R.anim.slide_out_right_scene_snapshot);
            }
            else
            {
                 ft.setCustomAnimations(R.anim.slide_in_right_scene_snapshot, R.anim.slide_out_left_scene_snapshot);
            }
                    ft.hide(mCurrentFragment).show(mFragments.get(position))
                    .commit();
                    mCurrentFragment=mFragments.get(position);

        }
    }


}
