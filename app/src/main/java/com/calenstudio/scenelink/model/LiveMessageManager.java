package com.calenstudio.scenelink.model;

import android.content.Context;

import com.calenstudio.scenelink.R;
import com.calenstudio.scenelink.Util;
import com.calenstudio.scenelink.bean.PictureMessage;
import com.calenstudio.scenelink.bean.SceneInfo;
import com.calenstudio.scenelink.bean.SceneMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Chenzq on 2017-05-12.
 */

public class LiveMessageManager {
    private Context mContext;
    public LiveMessageManager(Context context, SceneInfo sceneInfo)
    {
     mContext=context;
    }
    private List<SceneMessage> mSceneMessages=new ArrayList<>();
    public int FetchLatestMsgs(Date date)
    {
        PictureMessage pm=new PictureMessage();
        pm.setId(UUID.randomUUID().toString());
        pm.getPicUris().add(Util.resourceIdToUri(mContext, R.drawable.aa));
        pm.getPicUris().add(Util.resourceIdToUri(mContext,R.drawable.bb));
        mSceneMessages.add(pm);
        return  mSceneMessages.size();
    }
}
