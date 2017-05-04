package com.calenstudio.scenelink.view.mainpage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.calenstudio.scenelink.R;
import com.calenstudio.scenelink.bean.SceneCategory;
import com.calenstudio.scenelink.model.DiscoverScenesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassifiedScenesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassifiedScenesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassifiedScenesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CATEGORY_ID = "categoryId";
    private static final String ARG_CATEGORY_NAME = "categoryName";

    // TODO: Rename and change types of parameters
    private SceneCategory mSceneCategory;
    private Unbinder mUnbinder;
    private OnFragmentInteractionListener mListener;
    @BindView(R.id.item_title)
     TextView mTextView;

    public ClassifiedScenesFragment() {
        // Required empty public constructor
    }


    public static ClassifiedScenesFragment newInstance(SceneCategory sc) {
        ClassifiedScenesFragment fragment = new ClassifiedScenesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY_ID, sc.getCategoryId());
        args.putString(ARG_CATEGORY_NAME, sc.getCategoryName());
        fragment.setArguments(args);
        fragment.mSceneCategory=sc;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSceneCategory.setCategoryId(getArguments().getString(ARG_CATEGORY_ID));
            mSceneCategory.setCategoryName(getArguments().getString(ARG_CATEGORY_NAME));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_categoried_scene, container, false);
        mUnbinder= ButterKnife.bind(this,view);
        mTextView.setText(mSceneCategory.getCategoryName());
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
}
