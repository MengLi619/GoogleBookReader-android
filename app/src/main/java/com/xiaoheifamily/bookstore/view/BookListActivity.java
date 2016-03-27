package com.xiaoheifamily.bookstore.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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

import com.xiaoheifamily.bookstore.App;
import com.xiaoheifamily.bookstore.R;
import com.xiaoheifamily.bookstore.databinding.BookListActivityBinding;
import com.xiaoheifamily.bookstore.widget.recyclerview.DividerItemDecoration;
import com.xiaoheifamily.bookstore.widget.recyclerview.EndlessRecyclerView;

import java.net.ConnectException;

public class BookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BookListActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.book_list_activity);
        binding.setModel(((App) getApplication()).getViewModelComponent().getBookListViewModel());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // init recycler view
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.book_list);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);

        // init swipe refresh layout
        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.post(() -> {
            refreshLayout.setRefreshing(true);
            binding.getModel().refresh();
        });

        // handle search operation
        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            String query = getIntent().getStringExtra(SearchManager.QUERY);
            refreshLayout.post(() -> {
                refreshLayout.setRefreshing(true);
                binding.getModel().search(query);
            });
        }

        // bind view model events
        binding.getModel().setOnError(ex -> {
            if (ex instanceof ConnectException) {
                CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.main_layout);
                Snackbar.make(layout, "Network Error", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.getModel().setOnRefreshFinished(() -> {
            refreshLayout.setRefreshing(false);
        });

        binding.getModel().setOnLoadMoreFinished(() -> {
            ((EndlessRecyclerView) recyclerView).setLoading(false);
        });

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

        return true;
    }
}
