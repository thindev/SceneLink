package com.calenstudio.scenelink.bean;

import java.util.ArrayList;


import java.util.List;

/**
 * Created by czqli on 2017-05-07.
 */

public class RecommendedGroup {
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
