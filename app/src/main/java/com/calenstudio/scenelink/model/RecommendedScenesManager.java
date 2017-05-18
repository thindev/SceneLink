package com.calenstudio.scenelink.model;

import com.calenstudio.scenelink.R;

import com.calenstudio.scenelink.bean.Location;
import com.calenstudio.scenelink.bean.RecommendedGroup;
import com.calenstudio.scenelink.bean.SceneInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        List<SceneInfo> ss=rg.getSceneInfos();
        SceneInfo si=new SceneInfo();
        si.setId(UUID.randomUUID().toString());
        si.setImg(R.drawable.aa);
        si.setBeginTime(new Date());
        si.setEndTime(new Date());
        si.setName("2017深圳智能时代数字化产品展览会");
        ss.add(si);
        si=new SceneInfo();
        si.setId(UUID.randomUUID().toString());
        si.setImg(R.drawable.bb);
        si.setBeginTime(new Date());
        si.setEndTime(new Date());
        si.setName("2017第一季度苹果电子产品新品发现会");
        ss.add(si);
        si=new SceneInfo();
        si.setId(UUID.randomUUID().toString());
        si.setImg(R.drawable.cc);
        si.setBeginTime(new Date());
        si.setEndTime(new Date());
        si.setName("第28届维娜斯杯全球华人歌手大赛广东赛区");
        ss.add(si);
        rg.setLayoutType(LAYOUT_TYPE_BANNER);
        mRecommendedGroupList.add(rg);
        rg=new RecommendedGroup();
        rg.setGroupId("1");
        rg.setGroupName("人气现场");
        ss=rg.getSceneInfos();
         si=new SceneInfo();
        si.setId(UUID.randomUUID().toString());
        si.setImg(R.drawable.aa);
        si.setBeginTime(new Date());
        si.setEndTime(new Date());
        si.setName("2017深圳智能时代数字化产品展览会");
        si.hasMultiThumbNail=true;
        Location location=new Location();
        location.setAddress("广东省广州市越秀区中山六路89号");
        si.setLocation(location);
        ss.add(si);
        si=new SceneInfo();
        si.setId(UUID.randomUUID().toString());
        si.setImg(R.drawable.bb);
        si.setBeginTime(new Date());
        si.setEndTime(new Date());
        si.setName("2017第一季度苹果电子产品新品发现会");

        location=new Location();
        location.setAddress("广东省广州市海珠区新滘西路");
        si.setLocation(location);
        ss.add(si);
        si=new SceneInfo();
        si.setId(UUID.randomUUID().toString());
        si.setImg(R.drawable.cc);
        si.setBeginTime(new Date());
        si.setEndTime(new Date());
        si.setName("第28届维娜斯杯全球华人歌手大赛广东赛区");
        location=new Location();
        location.setAddress("广东省广州市海珠区英华街");
        si.setLocation(location);
        ss.add(si);
        rg.setLayoutType(LAYOUT_TYPE_VERBICAL_LIST);
        mRecommendedGroupList.add(rg);
        rg=new RecommendedGroup();
        rg.setGroupId("0");
        rg.setGroupName("猜你喜欢");
        rg.setLayoutType(LAYOUT_TYPE_TILE);
        mRecommendedGroupList.add(rg);
        rg=new RecommendedGroup();
        rg.setGroupId("0");
        rg.setGroupName("9");
        rg.setLayoutType(9);
        mRecommendedGroupList.add(rg);
    }
    public  RecommendedScenesManager()
    {
        this.InitRecommendedScenes();
    }


}
