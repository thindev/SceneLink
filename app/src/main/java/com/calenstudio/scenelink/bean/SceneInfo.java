package com.calenstudio.scenelink.bean;

import java.util.Date;

/**
 * Created by czqli on 2017-05-01.
 */

public class SceneInfo {

    private String mId;
    private String mName;
    private Location mLocation;
    private Date mBeginTime;
    private Date mEndTime;
    public boolean hasMultiThumbNail;


    public int getImg() {
        return mImg;
    }

    public void setImg(int img) {
        mImg = img;
    }

    private int mImg;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public Date getBeginTime() {
        return mBeginTime;
    }

    public void setBeginTime(Date beginTime) {
        mBeginTime = beginTime;
    }

    public Date getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Date endTime) {
        mEndTime = endTime;
    }
}
