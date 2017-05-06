package com.calenstudio.scenelink.view.mainpage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;

import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.calenstudio.scenelink.R;
import com.calenstudio.scenelink.model.DiscoverScenesManager;
import com.calenstudio.scenelink.view.basic.SlidingTabLayout;



import butterknife.BindView;


import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiscoverFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiscoverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    static final String LOG_TAG = "SlidingTabsBasicFragment";
    private final DiscoverScenesManager mDiscoverScenesManager;
    private SetSceneCategoryFragment mSetSceneCategoryFragment;
    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;

    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    private ViewPager mViewPager;
    @BindView(R.id.checkbox_set_scene_category)
    CheckBox mCb_setting;
    public DiscoverFragment() {
        // Required empty public constructor
        mDiscoverScenesManager=new DiscoverScenesManager();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscoverFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscoverFragment newInstance(String param1, String param2) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_discover, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set its PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_find);
        mViewPager.setAdapter(new DiscoverFragment.DiscoverFragmentsAdapter(getFragmentManager(),mDiscoverScenesManager));
        // END_INCLUDE (setup_viewpager)
        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // its PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs_find);
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return ContextCompat.getColor(getContext(),R.color.colorAccent);
            }

            @Override
            public int getDividerColor(int position) {
                return 0x00000000;
            }

            @Override
            public int getNormalColor(int position) {
                return ContextCompat.getColor(getContext(),R.color.itemIconInactiveColor);
            }
        });
        mSlidingTabLayout.setCustomTabView(R.layout.tab_title_scene_category,R.id.txt_title);
        mSlidingTabLayout.setViewPager(mViewPager);
        // END_INCLUDE (setup_slidingtablayout)
        mCb_setting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentTransaction ft= getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_top_set_scene_category,R.anim.slide_out_top_set_scene_category);
                if(isChecked)
                {
                   mSetSceneCategoryFragment=SetSceneCategoryFragment.newInstance("","");
                    ft.add(R.id.container_set_scene_category,mSetSceneCategoryFragment);
                    ft.commit();
                }
                else
                {
                   ft.remove(mSetSceneCategoryFragment);
                    ft.commit();
                    mSetSceneCategoryFragment=null;
                }
            }
        });
    }

        class DiscoverFragmentsAdapter extends FragmentStatePagerAdapter {
            DiscoverScenesManager mDiscoverScenesManager;
            RecommendFragment mRecommendFragment;
            public DiscoverFragmentsAdapter(FragmentManager fm,DiscoverScenesManager discoverScenesManager) {
                super(fm);
                mDiscoverScenesManager=discoverScenesManager;
            }

            @Override
            public Fragment getItem(int position) {
                if(position==0)
                {
                    if(mRecommendFragment==null)
                    {
                        mRecommendFragment=RecommendFragment.newInstance(mDiscoverScenesManager.getSceneCategories().get(position));
                    }
                    return mRecommendFragment;
                }
                return ClassifiedScenesFragment.newInstance(mDiscoverScenesManager.getSceneCategories().get(position));
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
               if(position!=0)
                super.destroyItem(container, position, object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mDiscoverScenesManager.getSceneCategories().get(position).getCategoryName();
            }

            @Override
            public int getCount() {
                return mDiscoverScenesManager.getSceneCategories().size();
            }
        }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

    }

    class RecommendedScenesView extends LinearLayout
    {
        public RecommendedScenesView(Context context) {
            super(context);
        }

        public RecommendedScenesView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public RecommendedScenesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }


    }
}
