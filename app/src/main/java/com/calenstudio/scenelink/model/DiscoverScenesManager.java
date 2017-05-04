package com.calenstudio.scenelink.model;

import com.calenstudio.scenelink.bean.SceneCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenzq on 2017-05-03.
 */

public class DiscoverScenesManager {
    public List<SceneCategory> getSceneCategories() {
        return mSceneCategories;
    }

    private List<SceneCategory> mSceneCategories;
    public DiscoverScenesManager()
    {
       this.initSceneCategories();
    }

    private void initSceneCategories()
    {
        mSceneCategories=new ArrayList<>();
        SceneCategory sc=new SceneCategory();
        sc.setCategoryId("0000");
        sc.setCategoryName("推荐");
        mSceneCategories.add(sc);
        //1
        sc=new SceneCategory();
        sc.setCategoryId("0001");
        sc.setCategoryName("讲座");
        mSceneCategories.add(sc);
        //2
        sc=new SceneCategory();
        sc.setCategoryId("0002");
        sc.setCategoryName("庆典");
        mSceneCategories.add(sc);
        //3
        sc=new SceneCategory();
        sc.setCategoryId("0003");
        sc.setCategoryName("体育");
        mSceneCategories.add(sc);
        //4
        sc=new SceneCategory();
        sc.setCategoryId("0004");
        sc.setCategoryName("娱乐");
        mSceneCategories.add(sc);
        //5
        sc=new SceneCategory();
        sc.setCategoryId("0005");
        sc.setCategoryName("营销");
        mSceneCategories.add(sc);
        //6
        sc=new SceneCategory();
        sc.setCategoryId("0006");
        sc.setCategoryName("修闲");
        mSceneCategories.add(sc);
        //7
        sc=new SceneCategory();
        sc.setCategoryId("0007");
        sc.setCategoryName("文艺");
        mSceneCategories.add(sc);
    }
}
