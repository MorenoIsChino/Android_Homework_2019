package com.example.learning.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.app.shop.mylibrary.base.BaseActivity;
import com.example.learning.R;
import com.example.learning.interfaces.OnCalendarItemClick;
import com.example.learning.interfaces.OnSignedSuccess;
import com.example.learning.utils.DateUtil;
import com.example.learning.widgts.SignDate;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseCalendarActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.signDate)
    SignDate signDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_calendar);
        ButterKnife.bind(this);

        initData();
        tvTitle.setText("教学日历");
    }

    private void initData() {
        List list = new ArrayList();

        //这里是默认哪天选中，根据需要进行修改
        list.add(0);
        list.add(5);
        list.add(13);
        list.add(22);
        list.add(30);
        signDate.setHaveNew(list);

        signDate.setOnItemClick(new OnCalendarItemClick() {
            @Override
            public void onClick(int position) {
                Logger.e("---------" + position);

                Intent intent = new Intent(CourseCalendarActivity.this, CourseCalendarDetailActivity.class);
                intent.putExtra("date", DateUtil.getCurrentYearAndMonth() + position + "日");
                showActivity(CourseCalendarActivity.this, intent);
            }
        });
    }

    @OnClick(R.id.imgv_return)
    public void onViewClicked() {
        onBackPressed();
    }
}
