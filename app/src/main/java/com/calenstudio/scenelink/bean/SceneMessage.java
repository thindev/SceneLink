package com.calenstudio.scenelink.bean;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenzq on 2017-05-12.
 */

public class SceneMessage {
    private  String mId;
    private String mTitle;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }



    public List<Comment> getComments() {
        return mComments;
    }




    private List<Comment> mComments=new ArrayList<>();
}
