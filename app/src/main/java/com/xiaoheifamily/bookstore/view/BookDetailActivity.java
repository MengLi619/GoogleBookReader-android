package com.xiaoheifamily.bookstore.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.xiaoheifamily.bookstore.R;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putSerializable(BookDetailFragment.ARG_BOOK,
                    getIntent().getSerializableExtra(BookDetailFragment.ARG_BOOK));

            BookDetailFragment fragment = new BookDetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.book_detail_container, fragment)
                    .commit();
        }
    }
}