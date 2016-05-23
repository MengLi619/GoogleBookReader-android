package com.xiaoheifamily.googlebookreader.view;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.xiaoheifamily.googlebookreader.App;
import com.xiaoheifamily.googlebookreader.R;
import com.xiaoheifamily.googlebookreader.databinding.BookListFragmentBinding;
import com.xiaoheifamily.googlebookreader.viewmodel.BookListViewModel;
import com.xiaoheifamily.googlebookreader.widget.recyclerview.DividerItemDecoration;
import com.xiaoheifamily.googlebookreader.widget.recyclerview.EndlessRecyclerView;

public class BookListFragment extends Fragment {

    private BookListViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        model = ((App) getActivity().getApplication()).getViewModelComponent().getBookListViewModel();

        View view = inflater.inflate(R.layout.book_list_fragment, container, false);
        BookListFragmentBinding binding = BookListFragmentBinding.bind(view);
        binding.setModel(model);

        // setup recycler view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.book_list);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(view.getContext(),
                DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);

        // setup swipe refresh layout
        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);

        // bind view model events
        model.setOnError(ex -> {
            CoordinatorLayout layout = (CoordinatorLayout) view.findViewById(R.id.main_layout);
            Snackbar.make(layout, ex.getMessage(), Snackbar.LENGTH_SHORT).show();
        });

        model.setOnRefreshing(() ->
                refreshLayout.setRefreshing(true));

        model.setOnRefreshFinished(() ->
                refreshLayout.setRefreshing(false));

        model.setOnLoadMoreFinished(() ->
                ((EndlessRecyclerView) recyclerView).setLoading(false));

        // start refresh
        model.refresh();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                model.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }
}