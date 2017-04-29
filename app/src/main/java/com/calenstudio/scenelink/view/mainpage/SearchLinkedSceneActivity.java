package com.calenstudio.scenelink.view.mainpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.Toolbar;


import com.calenstudio.scenelink.R;



import butterknife.BindView;


import butterknife.ButterKnife;

public class SearchLinkedSceneActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_search)
    Toolbar mActionbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_linked_scene);
        ButterKnife.bind(this);
        this.setSupportActionBar(mActionbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
