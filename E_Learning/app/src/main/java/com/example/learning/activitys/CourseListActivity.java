package com.example.learning.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.shop.mylibrary.base.BaseActivity;
import com.example.learning.MainActivity;
import com.example.learning.R;
import com.example.learning.adapters.CourseItemListAdapter;
import com.example.learning.adapters.MyCourseAdapter;
import com.example.learning.beans.CourseItem;
import com.example.learning.beans.MyCourseBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseListActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.list)
    ListView listView;

    CourseItemListAdapter adapter;
    MyCourseBean bean;
    List<CourseItem> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        Bundle bundle = getIntent().getExtras();
        bean = (MyCourseBean) bundle.getSerializable("bean");
        tvTitle.setText(bean.getTitle());

        // TODO: data source
        if(bean.getList()!=null){
            list.addAll(bean.getList());
        }
        adapter = new CourseItemListAdapter(this, (ArrayList) list, R.layout.item_my_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", list.get(position));
                showActivity(CourseListActivity.this, CourseDetailActivity.class, bundle);
            }
        });
    }

    @OnClick(R.id.imgv_return)
    public void onViewClicked() {
        onBackPressed();
    }
}
