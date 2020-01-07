package com.example.learning.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.widgts.ScrollListView;
import com.example.learning.R;
import com.example.learning.adapters.CourseNewTipsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseCalendarDetailActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.listview)
    ScrollListView listview;


    List list = new ArrayList();
    CourseNewTipsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_calendar_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        tvTitle.setText("本日更新");
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        tvDate.setText(date);

        //这里数据根据需要进行修改
        list.add("Java");
        list.add("C");
        list.add("Python");

        adapter = new CourseNewTipsAdapter(this, (ArrayList) list, R.layout.item_course_new_list);
        listview.setAdapter(adapter);

    }

    @OnClick(R.id.imgv_return)
    public void onViewClicked() {

        onBackPressed();
    }
}
