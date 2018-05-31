package com.margo.postcard.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.margo.postcard.GridSpacingItemDecoration;
import com.margo.postcard.QueryPreferences;
import com.margo.postcard.R;

/**
 * Created by Margo on 31.05.2018.
 */

public class SearchGalleryFragment extends Fragment {//extends PhotoGalleryFragment{

    private final String TAG = "SearchGalleryFragment";
    private SearchView mSearchView;
    private PhotoGalleryFragment.OnFragmentInteractionListener mListener;
    View view;

    public static SearchGalleryFragment newInstance() {
        return new SearchGalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        mSearchView = (SearchView) view.findViewById(R.id.menu_item_search);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "QueryTextSubmit: " + s);
                QueryPreferences.setStoredQuery(getActivity(), s);
                onChildViewCreated(view);
                onAttachChild(getContext());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "QueryTextChange: " + s);
                return false;
            }
        });

        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = QueryPreferences.getStoredQuery(getActivity());
                mSearchView.setQuery(query, false);
            }
        });

        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                onDetach();
                return false;
            }
        });
    }

    public void onChildViewCreated(View view) {
        Fragment childFragment = new PhotoGalleryFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.linear_search_container, childFragment).commit();
    }

    public void onAttachChild(Context context) {
        if (context instanceof PhotoGalleryFragment.OnFragmentInteractionListener) {
            mListener = (PhotoGalleryFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        QueryPreferences.setStoredQuery(getActivity(), null);
    }
}
