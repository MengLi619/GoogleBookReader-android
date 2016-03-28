package com.xiaoheifamily.googlebookreader.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.xiaoheifamily.googlebookreader.R;
import com.xiaoheifamily.googlebookreader.databinding.BookDetailActivityBinding;
import com.xiaoheifamily.googlebookreader.model.Book;
import com.xiaoheifamily.googlebookreader.viewmodel.BookDetailViewModel;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Book book = (Book) getIntent().getSerializableExtra(BookDetailFragment.ARG_BOOK);

        BookDetailActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.book_detail_activity);
        binding.setModel(new BookDetailViewModel(book));

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putSerializable(BookDetailFragment.ARG_BOOK, book);

            BookDetailFragment fragment = new BookDetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.book_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}