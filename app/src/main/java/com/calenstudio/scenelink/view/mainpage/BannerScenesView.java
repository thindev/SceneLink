package com.calenstudio.scenelink.view.mainpage;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.calenstudio.scenelink.R;
import com.calenstudio.scenelink.Util;
import com.calenstudio.scenelink.bean.SceneInfo;
import com.calenstudio.scenelink.model.RecommendedScenesManager;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenzq on 2017-05-04.
 */

public class BannerScenesView extends FrameLayout {

    public RecommendedScenesManager.RecommendedGroup getRecommendedGroup() {
        return mRecommendedGroup;
    }

    public void setRecommendedGroup(RecommendedScenesManager.RecommendedGroup recommendedGroup) {
        mRecommendedGroup = recommendedGroup;
        this.createView(getContext());
        List<Uri> uris=new ArrayList<>();
        for (SceneInfo si:mRecommendedGroup.getSceneInfos()) {
            uris.add(Util.resourceIdToUri(getContext(),si.getImg()));
        }
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        mBanner.setImages(uris);
        mBanner.start();
    }
    private void createView(Context cxt)
    {
       View view= LayoutInflater.from(cxt).inflate(R.layout.merge_scenes_banner,this,true);
       mBanner=(Banner) this.findViewById(R.id.banner_scenes);
    }

    private RecommendedScenesManager.RecommendedGroup mRecommendedGroup;
    private Banner mBanner;

    public BannerScenesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public BannerScenesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
