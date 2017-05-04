package com.calenstudio.scenelink.model;

import com.calenstudio.scenelink.bean.SceneInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenzq on 2017-05-03.
 */

public class RecommendedScenesManager {
    public static final int LAYOUT_TYPE_BANNER=1;
    public static final int LAYOUT_TYPE_VERBICAL_LIST=2;
    public static final int LAYOUT_TYPE_HORIZONTAL_LIST=3;
    public static final int LAYOUT_TYPE_TILE=4;

    public List<RecommendedGroup> getRecommendedGroupList() {
        return mRecommendedGroupList;
    }

    private List<RecommendedGroup> mRecommendedGroupList;
    private void InitRecommendedScenes()
    {
        mRecommendedGroupList=new ArrayList<>();
        RecommendedGroup rg=new RecommendedGroup();
        rg.setGroupId("0");
        rg.setGroupName("推广");
        rg.setLayoutType(LAYOUT_TYPE_BANNER);
        mRecommendedGroupList.add(rg);
        rg=new RecommendedGroup();
        rg.setGroupId("1");
        rg.setGroupName("火热进行中");
        rg.setLayoutType(LAYOUT_TYPE_BANNER);
        mRecommendedGroupList.add(rg);
        rg=new RecommendedGroup();
        rg.setGroupId("0");
        rg.setGroupName("猜你喜欢");
        rg.setLayoutType(LAYOUT_TYPE_BANNER);
        mRecommendedGroupList.add(rg);
        rg=new RecommendedGroup();
        rg.setGroupId("0");
        rg.setGroupName("即将进行");
        rg.setLayoutType(LAYOUT_TYPE_BANNER);
        mRecommendedGroupList.add(rg);
    }
    public void RecommendedScenesManager()
    {
        this.InitRecommendedScenes();
    }

    public class RecommendedGroup
    {
        public String getGroupId() {
            return mGroupId;
        }

        public void setGroupId(String groupId) {
            mGroupId = groupId;
        }

        public String getGroupName() {
            return mGroupName;
        }

        public void setGroupName(String groupName) {
            mGroupName = groupName;
        }

        public int getLayoutType() {
            return mLayoutType;
        }

        public void setLayoutType(int layoutType) {
            mLayoutType = layoutType;
        }

        public List<SceneInfo> getSceneInfos() {
            return mSceneInfos;
        }

        private String mGroupId;
        private String mGroupName;
        private int mLayoutType;
        private List<SceneInfo> mSceneInfos=new ArrayList<>();

    }
}
