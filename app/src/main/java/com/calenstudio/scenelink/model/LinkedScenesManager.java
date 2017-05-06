package com.calenstudio.scenelink.model;


import com.calenstudio.scenelink.R;

import com.calenstudio.scenelink.bean.SceneInfo;



import java.util.ArrayList;

import java.util.Date;
import java.util.List;


import java.util.UUID;

/**
 * Created by czq on 2017-05-01.
 */

public class LinkedScenesManager {

    private List<SceneInfo> mSceneInfos;
    private ScenesManageEventHandler mScenesManageEventHandler;


    public LinkedScenesManager()
    {
        mSceneInfos = new ArrayList<>();
    }

    public List<SceneInfo> getSceneInfos() {
        return mSceneInfos;
    }

    public void setScenesManageEventHandler(ScenesManageEventHandler scenesManageEventHandler) {
        mScenesManageEventHandler = scenesManageEventHandler;
    }

    /**
     * 异步获取最新场景列表
     */
    public void FetchSceneAsync()
    {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                if(mScenesManageEventHandler!=null) {
                    mScenesManageEventHandler.onStartedFetchingScenes();
                }
                    try {
                        Thread.sleep(1000);
                        SceneInfo si=new SceneInfo();
                        si.setId(UUID.randomUUID().toString());
                        si.setImg(R.drawable.aa);
                        si.setBeginTime(new Date());
                        si.setEndTime(new Date());
                        si.setName("2017深圳智能时代智能时代数字化产品能时代数字化产品展览会");
                        mSceneInfos.add(si);
                        si=new SceneInfo();
                        si.setId(UUID.randomUUID().toString());
                        si.setImg(R.drawable.bb);
                        si.setBeginTime(new Date());
                        si.setEndTime(new Date());
                        si.setName("2017第一季度苹果电子产品新品发现会");
                        mSceneInfos.add(si);
                        si=new SceneInfo();
                        si.setId(UUID.randomUUID().toString());
                        si.setImg(R.drawable.cc);
                        si.setBeginTime(new Date());
                        si.setEndTime(new Date());
                        si.setName("第28届维娜斯杯全球华人歌手大赛广东赛区");
                        mSceneInfos.add(si);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        if(mScenesManageEventHandler!=null) {
                            mScenesManageEventHandler.onError();
                        }
                    }
                    if(mScenesManageEventHandler!=null){
                    mScenesManageEventHandler.onFinishedFetchingScenes();
                    }
                };
        };
        Thread thread=new Thread(runnable);
        thread.start();
    }
    public interface ScenesManageEventHandler{
        void onStartedFetchingScenes();
        void onFinishedFetchingScenes();
        void onError();
    }
    public class ErrorMessage {

    }
}
