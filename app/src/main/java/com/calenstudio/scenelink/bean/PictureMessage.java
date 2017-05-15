package com.calenstudio.scenelink.bean;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenzq on 2017-05-12.
 */

public class PictureMessage extends SceneMessage {
    private List<Uri> mPicUris=new ArrayList<>();
    public List<Uri> getPicUris() {
        return mPicUris;
    }
}
