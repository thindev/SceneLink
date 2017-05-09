package com.calenstudio.scenelink.view.mainpage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;


import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.calenstudio.scenelink.R;
import com.calenstudio.scenelink.Util;
import com.calenstudio.scenelink.bean.RecommendedGroup;
import com.calenstudio.scenelink.bean.SceneCategory;
import com.calenstudio.scenelink.bean.SceneInfo;
import com.calenstudio.scenelink.model.RecommendedScenesManager;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecommendFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CATEGORY_ID = "categoryId";
    private static final String ARG_CATEGORY_NAME = "categoryName";

    private SceneCategory mSceneCategory;
    private RecommendedScenesManager mRecommendedScenesManager;
    private ListViewCompat mListView;


    private OnFragmentInteractionListener mListener;

    public RecommendFragment() {
        // Required empty public constructor
    }
    public static RecommendFragment newInstance(SceneCategory sc) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY_ID, sc.getCategoryId());
        args.putString(ARG_CATEGORY_NAME, sc.getCategoryName());
        fragment.setArguments(args);
        fragment.mSceneCategory=sc;
        fragment.mRecommendedScenesManager=new RecommendedScenesManager();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mListView=(ListViewCompat) inflater.inflate(R.layout.fragment_recommend, container, false);
        mListView.setAdapter(new SceneGroupAdapter(mRecommendedScenesManager.getRecommendedGroupList()));
        return  mListView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

    private class SceneGroupAdapter extends BaseAdapter
    {
        public SceneGroupAdapter(List<RecommendedGroup> groups)
        {
            mRecommendedGroups=groups;
        }
        private List<RecommendedGroup> mRecommendedGroups;
        private LayoutInflater mInflater=LayoutInflater.from(getContext());
        @Override
        public int getCount() {
            return mRecommendedScenesManager.getRecommendedGroupList().size();
        }

        @Override
        public Object getItem(int i) {
            return mRecommendedGroups.get(i);
        }

        @Override
        public long getItemId(int i) {
            return mRecommendedScenesManager.getRecommendedGroupList().get(i).getGroupId().hashCode();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            RecommendedGroup group=mRecommendedGroups.get(i);
            switch (group.getLayoutType())
            {
                case RecommendedScenesManager.LAYOUT_TYPE_BANNER:
                    BannerScenesViewHolder bannerScenesViewHolder=null;
                    if(view!=null&&view.getTag()!=null)
                    {
                        bannerScenesViewHolder=(BannerScenesViewHolder) view.getTag();
                    }
                    if(bannerScenesViewHolder==null)
                    {
                        view=mInflater.inflate(R.layout.list_item_scenes_banner,viewGroup,false);
                        Banner banner=(Banner)view.findViewById(R.id.banner_scenes);
                        bannerScenesViewHolder=new BannerScenesViewHolder(getContext(),banner);
                        view.setTag(bannerScenesViewHolder);
                        bannerScenesViewHolder.setRecommendedGroup(group);
                        bannerScenesViewHolder.mBanner.start();
                    }
                    else if(bannerScenesViewHolder.getRecommendedGroup()!=group) {
                        bannerScenesViewHolder.resetViews();
                        bannerScenesViewHolder.setRecommendedGroup(group);
                    }
                        break;
                case RecommendedScenesManager.LAYOUT_TYPE_VERBICAL_LIST:
                    VerticalListViewHolder verticalListViewHolder=null;
                    if(view!=null&&view.getTag()!=null)
                    {
                        verticalListViewHolder=(VerticalListViewHolder) view.getTag();
                    }
                    if(verticalListViewHolder==null)
                    {
                        view=mInflater.inflate(R.layout.list_item_scenes_vertical_list,viewGroup,false);
                        verticalListViewHolder=new VerticalListViewHolder(getContext(),view);
                        view.setTag(verticalListViewHolder);
                        verticalListViewHolder.setRecommendedGroup(group);
                    }
                    else if(verticalListViewHolder.getRecommendedGroup()!=group)
                    {
                        verticalListViewHolder.resetViews();
                        verticalListViewHolder.setRecommendedGroup(group);
                    }

                    break;
                case RecommendedScenesManager.LAYOUT_TYPE_TILE:
                    view=  new View(getContext());
                    break;
                case RecommendedScenesManager.LAYOUT_TYPE_HORIZONTAL_LIST:
                    view= new View(getContext());
                    break;
                default:
                    view= new View(getContext());
                    break;
            }
            return  view;
        }
    }

    private static abstract class ViewHolderBase
    {
        private int mLayoutType;
        protected Context mContext;
        protected RecommendedGroup mRecommendedGroup;
        public ViewHolderBase(Context cxt)
        {
            mContext=cxt;
        }



        public abstract void resetViews();
        public  void setRecommendedGroup(RecommendedGroup recommendedGroup)
        {
            mRecommendedGroup=recommendedGroup;
        }
        public  RecommendedGroup getRecommendedGroup( ) {
         return  mRecommendedGroup;
        }

    }

    private static class BannerScenesViewHolder extends ViewHolderBase
    {
        public Banner mBanner;

        public BannerScenesViewHolder(Context cxt,Banner banner) {
            super(cxt);
            mBanner=banner;
        }

        public void setRecommendedGroup(RecommendedGroup recommendedGroup) {
            mRecommendedGroup = recommendedGroup;
            List<Uri> uris=new ArrayList<>();
            for (SceneInfo si:mRecommendedGroup.getSceneInfos()) {
                uris.add(Util.resourceIdToUri(mContext,si.getImg()));
            }
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            });
            mBanner.setImages(uris);
        }


        @Override
        public void resetViews() {
            mBanner.setImages(new ArrayList<String>(0));
            mRecommendedGroup=null;
            mBanner.stopAutoPlay();
        }
    }
    private  static class VerticalListViewHolder extends ViewHolderBase {
        private LinearLayout mLayout;
        private TextView mtv_title;
        private Button mButton_more;
        public VerticalListViewHolder(Context cxt,@NonNull View view) {
            super(cxt);
            mLayout=(LinearLayout) view.findViewById(R.id.verticalList_scenes) ;
            mtv_title=(TextView)view.findViewById(R.id.tv_sceneGrupTitle);
            mButton_more=(Button)view.findViewById(R.id.button_more);
        }

        @Override
        public void resetViews() {
            mLayout.removeAllViews();
            mtv_title.setText("");
            mButton_more.setOnClickListener(null);
        }
        @Override
        public void setRecommendedGroup(RecommendedGroup recommendedGroup) {
            super.setRecommendedGroup(recommendedGroup);
            creatViewList(recommendedGroup.getSceneInfos());
            mtv_title.setText(recommendedGroup.getGroupName());
            mButton_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        private void creatViewList(List<SceneInfo> scenes)
        {
            for (SceneInfo si:scenes) {

                View view=null;
                if(si.hasMultiThumbNail)
                {
                    view= LayoutInflater.from(mContext).inflate(R.layout.list_item_single_scene_multi_images,mLayout,false);
                }
                else
                {
                    view= LayoutInflater.from(mContext).inflate(R.layout.list_item_single_scene,mLayout,false);
                }
                mLayout.addView(view);
                TextView sceneName=(TextView)view.findViewById(R.id.tv_scene_name);
                TextView startTime=(TextView)view.findViewById(R.id.tv_scene_startTime);
                TextView address=(TextView)view.findViewById(R.id.tv_scene_address);
                ImageView img=(ImageView)view.findViewById(R.id.image_scene);
                sceneName.setText(si.getName());
                startTime.setText(Util.formatDate(si.getBeginTime(),"MM月dd日 HH:mm"));
                address.setText(si.getLocation().getAddress());
                img.setImageResource(si.getImg());
            }
        }

    }
    private  static class HorizontalListViewHolder extends ViewHolderBase {
        public HorizontalListViewHolder(Context cxt) {
            super(cxt);
        }



        @Override
        public void resetViews() {

        }
        @Override
        public void setRecommendedGroup(RecommendedGroup recommendedGroup) {

        }
    }
    private  static class TiledViewHolder extends ViewHolderBase {
        public TiledViewHolder(Context cxt) {
            super(cxt);
        }



        @Override
        public void resetViews() {

        }
        @Override
        public void setRecommendedGroup(RecommendedGroup recommendedGroup) {

        }
    }
}
