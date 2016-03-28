package com.xiaoheifamily.googlebookreader.view;

import android.app.SearchManager;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.SearchView;

import com.xiaoheifamily.googlebookreader.App;
import com.xiaoheifamily.googlebookreader.R;
import com.xiaoheifamily.googlebookreader.databinding.BookListActivityBinding;
import com.xiaoheifamily.googlebookreader.viewmodel.BookListViewModel;
import com.xiaoheifamily.googlebookreader.widget.recyclerview.DividerItemDecoration;
import com.xiaoheifamily.googlebookreader.widget.recyclerview.EndlessRecyclerView;

import java.net.ConnectException;

public class BookListActivity extends AppCompatActivity {

    private BookListViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create view model
        model = ((App) getApplication()).getViewModelComponent().getBookListViewModel();

        // bind view model
        BookListActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.book_list_activity);
        binding.setModel(model);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // init recycler view
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.book_list);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);

        // init swipe refresh layout
        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);

        // bind view model events
        model.setOnError(ex -> {
            if (ex instanceof ConnectException) {
                CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.main_layout);
                Snackbar.make(layout, "Network Error", Snackbar.LENGTH_SHORT).show();
            }
        });

        model.setOnRefreshing(() ->
                refreshLayout.setRefreshing(true));

        model.setOnRefreshFinished(() ->
                refreshLayout.setRefreshing(false));

        model.setOnLoadMoreFinished(() ->
                ((EndlessRecyclerView) recyclerView).setLoading(false));

        // start refresh
        model.refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
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

        return true;
    }
}
