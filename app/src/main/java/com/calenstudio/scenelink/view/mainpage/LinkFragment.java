package com.calenstudio.scenelink.view.mainpage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.calenstudio.scenelink.R;

import com.calenstudio.scenelink.bean.SceneInfo;
import com.calenstudio.scenelink.model.LinkedScenesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LinkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LinkFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rv_linkedSceneList)
    RecyclerView mRvLinkedSceneList;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private LinkedScenesManager mLinkedScenesManager;
    private LinkedScenesAdapter mLinkedScenesAdapter;
    private Unbinder mUnbinder;

    public LinkFragment() {
        mLinkedScenesManager = new LinkedScenesManager();
        mLinkedScenesManager.setScenesManageEventHandler(new LinkedScenesManager.ScenesManageEventHandler() {
            @Override
            public void onStartedFetchingScenes() {

            }

            @Override
            public void onFinishedFetchingScenes() {
                if (mSwipeRefreshLayout != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mLinkedScenesAdapter.notifyDataSetChanged();
                            mRvLinkedSceneList.smoothScrollToPosition(mLinkedScenesManager.getSceneInfos().size());
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LinkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LinkFragment newInstance(String param1, String param2) {
        LinkFragment fragment = new LinkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_link, container, false);
        mUnbinder= ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLinkedScenesManager.FetchSceneAsync();
            }
        });
        mLinkedScenesAdapter = new LinkedScenesAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,true);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setStackFromEnd(true);
        mRvLinkedSceneList.setLayoutManager(linearLayoutManager);
        mRvLinkedSceneList.setAdapter(mLinkedScenesAdapter);
        mRvLinkedSceneList.setItemAnimator(new DefaultItemAnimator());
        mRvLinkedSceneList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onDestroyView() {
        if(mUnbinder!=null)
        {
            mUnbinder.unbind();
        }
        super.onDestroyView();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

    }

    class LinkedScenesAdapter extends RecyclerView.Adapter<LinkedScenesAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.view_holder_linked_scene, parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            SceneInfo sceneInfo=mLinkedScenesManager.getSceneInfos().get(position);
            holder.Bind(sceneInfo);
        }

        @Override
        public int getItemCount() {
            return mLinkedScenesManager.getSceneInfos().size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_scene_name)
            TextView mTvSceneName;
            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
            public void Bind(SceneInfo sceneInfo)
            {
                mTvSceneName.setText(sceneInfo.getName());
            }
        }


    }
}
