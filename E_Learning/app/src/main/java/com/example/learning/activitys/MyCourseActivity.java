package com.example.learning.activitys;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.shop.mylibrary.base.BaseActivity;
import com.example.learning.R;
import com.example.learning.adapters.MyCourseAdapter;
import com.example.learning.beans.CourseItem;
import com.example.learning.beans.MyCourseBean;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCourseActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.list)
    ListView listView;

    MyCourseAdapter adapter;

    List<MyCourseBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_course);
        ButterKnife.bind(this);
        tvTitle.setText("我的课程");
        initList();
    }

    private void initList() {

        // TODO: data source

        list = DataSupport.findAll(MyCourseBean.class);
        if (list != null && list.size() > 0) {
            Logger.e("-----------数据库取数据--list：" + list.size());
            list = DataSupport.findAll(MyCourseBean.class);
        } else {
            initData();
        }

        adapter = new MyCourseAdapter(this, (ArrayList) list, R.layout.item_my_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", list.get(position));
                showActivity(MyCourseActivity.this, CourseListActivity.class, bundle);
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 4; i++) {
            //一级列表
            MyCourseBean bean = new MyCourseBean();
            if (i == 0) {
                bean.setTitle("JAVA");
                //二级列表
                List<CourseItem> list_item = new ArrayList<>();
                CourseItem item_1 = new CourseItem();
                CourseItem item_2 = new CourseItem();
                item_1.setName("Lesson 1 : 面向对象编程");
                item_2.setName("Lesson 2 : Java 基础");
                list_item.add(item_1);
                list_item.add(item_2);
                bean.setList(list_item);
            } else if (i == 1) {
                bean.setTitle("计算机组成原理");
                //二级列表
                List<CourseItem> list_item = new ArrayList<>();
                CourseItem item_1 = new CourseItem();
                CourseItem item_2 = new CourseItem();
                item_1.setName("Lesson 1 : 计算机体系结构");
                item_2.setName("Lesson 2 : 总线");
                list_item.add(item_1);
                list_item.add(item_2);
                bean.setList(list_item);

            } else if (i == 2) {
                bean.setTitle("数据库建表规则");
                //二级列表
                List<CourseItem> list_item = new ArrayList<>();
                CourseItem item_1 = new CourseItem();
                CourseItem item_2 = new CourseItem();
                item_1.setName("Lesson 1 : 数据库入门");
                item_2.setName("Lesson 2 : 数据库进阶");
                list_item.add(item_1);
                list_item.add(item_2);
                bean.setList(list_item);
            } else if (i == 3) {
                bean.setTitle("缓存优化");
                //二级列表
                List<CourseItem> list_item = new ArrayList<>();
                CourseItem item_1 = new CourseItem();
                CourseItem item_2 = new CourseItem();
                item_1.setName("Lesson 1 :优化入门");
                item_2.setName("Lesson 2 :优化进阶");
                list_item.add(item_1);
                list_item.add(item_2);
                bean.setList(list_item);
            } else {
                bean.setTitle("线程异步操作");
                //二级列表
                List<CourseItem> list_item = new ArrayList<>();
                CourseItem item_1 = new CourseItem();
                CourseItem item_2 = new CourseItem();
                item_1.setName("Lesson 1 : 线程异步入门");
                item_2.setName("Lesson 2 : 线程异步进阶");
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
}
