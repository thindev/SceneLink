package com.calenstudio.scenelink.view.scene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.calenstudio.scenelink.R;
import com.calenstudio.scenelink.bean.SceneInfo;
import com.calenstudio.scenelink.view.mainpage.DiscoverFragment;
import com.calenstudio.scenelink.view.mainpage.LinkFragment;
import com.calenstudio.scenelink.view.mainpage.NearbyFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SceneActivity extends AppCompatActivity implements SceneLiveFragment.OnFragmentInteractionListener
,SceneMessagesFragment.OnFragmentInteractionListener,SceneMoreFragment.OnFragmentInteractionListener{
public final  static  String SCENE_NAME="sceneName";
    public final static String SCENE_ID="sceneId";
    private ArrayList<Fragment> mFragments;
    private Fragment mCurrentFragment;
    private BottomNavigationBar mBottomNavigationBar;
    private SceneInfo mSceneInfo;
    @BindView(R.id.viewpager_scene)
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        ButterKnife.bind(this);
        Intent intent= getIntent();
        mSceneInfo=new SceneInfo();
        mSceneInfo.setName(intent.getStringExtra(SCENE_NAME));
        mSceneInfo.setId(intent.getStringExtra(SCENE_ID));
        initBottomNavigationBar();
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private  void  initFragments()
    {
        mFragments=new ArrayList<>();
        mFragments.add(SceneLiveFragment.newInstance(mSceneInfo, "",""));
        mFragments.add(SceneMessagesFragment.newInstance("",""));
        mFragments.add(SceneMoreFragment.newInstance("",""));
        mCurrentFragment=mFragments.get(0);
    }
    private void initBottomNavigationBar() {
        if(mFragments==null){
            this.initFragments();
        }
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.bnv_live, R.string.scene_live))
                .addItem(new BottomNavigationItem(R.drawable.bnv_find, R.string.scene_message))
                .addItem(new BottomNavigationItem(R.drawable.bnv_nearby, R.string.scene_more))
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                selectContentView(position);
                mLastSelectPosition=position;
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
    private int mLastSelectPosition;
    private  void  selectContentView(int position) {
        mViewPager.setCurrentItem(position);
    }
}
