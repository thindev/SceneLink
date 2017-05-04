package com.calenstudio.scenelink.view.mainpage;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.calenstudio.scenelink.model.RecommendedScenesManager;

/**
 * Created by Chenzq on 2017-05-04.
 */

public class BannerScenesView extends FrameLayout {

    public RecommendedScenesManager.RecommendedGroup getRecommendedGroup() {
        return mRecommendedGroup;
    }

    public void setRecommendedGroup(RecommendedScenesManager.RecommendedGroup recommendedGroup) {
        mRecommendedGroup = recommendedGroup;
    }

    RecommendedScenesManager.RecommendedGroup mRecommendedGroup;
    public BannerScenesView(Context context) {
        super(context);
    }

    public BannerScenesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerScenesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
