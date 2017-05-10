package com.calenstudio.scenelink.view.scene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

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
    }
    private  void  initFragments()
    {
        mFragments=new ArrayList<>();
        mFragments.add(SceneLiveFragment.newInstance(mSceneInfo, "",""));
        mFragments.add(SceneMessagesFragment.newInstance("",""));
        mFragments.add(SceneMoreFragment.newInstance("",""));
        FragmentTransaction ft= this.getSupportFragmentManager()
                .beginTransaction();
        ft.add(R.id.content,mFragments.get(2),"2").hide(mFragments.get(2))
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
