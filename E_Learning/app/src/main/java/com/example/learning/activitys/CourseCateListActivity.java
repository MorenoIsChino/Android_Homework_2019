package com.example.learning.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.beans.EventMessage;
import com.example.learning.R;
import com.example.learning.adapters.CourseCateListAdapter;
import com.example.learning.adapters.MyCourseAdapter;
import com.example.learning.beans.CourseCateBean;
import com.example.learning.beans.CourseItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseCateListActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.list)
    ListView listView;

    CourseCateListAdapter adapter;
    CourseCateBean bean;

    List<CourseItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_cate_list);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {

        Bundle bundle = getIntent().getExtras();
        bean = (CourseCateBean) bundle.getSerializable("bean");
        if (bean.getList() != null) {
            list.addAll(bean.getList());
        }
        tvTitle.setText(bean.getTitle());

        adapter = new CourseCateListAdapter(this, (ArrayList) list, R.layout.item_course_cate_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", list.get(position));
                showActivity(CourseCateListActivity.this, CourseCateDetailActivity.class, bundle);
            }
        });


    }

    @OnClick(R.id.imgv_return)
    public void onViewClicked() {
        onBackPressed();

    }

    @Override
    public void onEvent(EventMessage msg) {
        super.onEvent(msg);
        if (msg.getMessageType() == EventMessage.CLICK_APPOINT) {
            CourseItem bean = (CourseItem) msg.getmObject();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().equals(bean.getName())) {
                    list.get(i).setPoint(bean.isPoint());
                }
            }
            adapter.notifyDataSetChanged();
        }
    }
}
