package com.calenstudio.scenelink.bean;

/**
 * Created by Chenzq on 2017-05-03.
 */

public class SceneCategory {

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String categoryName) {
        mCategoryName = categoryName;
    }

    public String getParentCategoryId() {
        return mParentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        mParentCategoryId = parentCategoryId;
    }

    private String mCategoryId;
    private String mCategoryName;
    private String mParentCategoryId;
}
