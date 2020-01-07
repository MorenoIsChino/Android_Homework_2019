package com.example.learning.activitys;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.beans.EventMessage;
import com.example.learning.R;
import com.example.learning.adapters.CourseCateAdapter;
import com.example.learning.adapters.MyCourseAdapter;
import com.example.learning.beans.CourseCateBean;
import com.example.learning.beans.CourseItem;
import com.example.learning.beans.MyCourseBean;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseCateActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.list)
    ListView listView;

    CourseCateAdapter adapter;

    List<CourseCateBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_cate);
        ButterKnife.bind(this);
        initList();
    }


    private void initList() {
        tvTitle.setText("课程分类");

        // TODO: data source
        initData();
        adapter = new CourseCateAdapter(this, (ArrayList) list, R.layout.item_course_cate_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", list.get(position));
                showActivity(CourseCateActivity.this, CourseCateListActivity.class, bundle);
            }
        });
    }

    private void initData() {

        for (int i = 0; i < 3; i++) {
            //一级列表
            CourseCateBean bean = new CourseCateBean();
            if (i == 0) {
                bean.setTitle("计算机");
                //二级列表
                List<CourseItem> list_item = new ArrayList<>();
                CourseItem item_1 = new CourseItem();
                CourseItem item_2 = new CourseItem();
                item_1.setName("JAVA");
                item_2.setName("C");
                list_item.add(item_1);
                list_item.add(item_2);
                bean.setList(list_item);
            } else if (i == 1) {
                bean.setTitle("数学");
                //二级列表
                List<CourseItem> list_item = new ArrayList<>();
                CourseItem item_1 = new CourseItem();
                CourseItem item_2 = new CourseItem();
                item_1.setName("微积分");
                item_2.setName("解析几何");
                list_item.add(item_1);
                list_item.add(item_2);
                bean.setList(list_item);

            } else if (i == 2) {
                bean.setTitle("语言");
                //二级列表
                List<CourseItem> list_item = new ArrayList<>();
                CourseItem item_1 = new CourseItem();
                CourseItem item_2 = new CourseItem();
                item_1.setName("中文诗歌鉴赏");
                item_2.setName("口语技巧");
                list_item.add(item_1);
                list_item.add(item_2);
                bean.setList(list_item);
            }

            list.add(bean);
        }
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
                for (int j = 0; j < list.get(i).getList().size(); j++) {
                    if (list.get(i).getList().get(j).getName().equals(bean.getName())) {
                        list.get(i).getList().get(j).setPoint(bean.isPoint());
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
    }
}
