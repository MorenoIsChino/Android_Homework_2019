package com.example.learning.activitys;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.utils.DialogUtil;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.example.learning.R;
import com.example.learning.beans.CourseItem;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.lang.invoke.CallSite;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class CourseCateDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_describe)
    TextView tvDescribe;
    @BindView(R.id.tv_appoint)
    TextView tvAppoint;

    CourseItem bean;

    List<CourseItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_cate_detail);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        Bundle bundle = getIntent().getExtras();
        bean = (CourseItem) bundle.getSerializable("bean");
        tvTitle.setText(bean.getName());

        tvDescribe.setText(bean.getName()+"的课程详情................");

        list = DataSupport.findAll(CourseItem.class);
        if (list != null && list.size() > 0) {
            Logger.e("-----------数据库取数据--list：" + list.size());
            list = DataSupport.findAll(CourseItem.class);
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(bean.getName())) {
                bean.setPoint(list.get(i).isPoint());
            }
        }

        //预约状态
        bean.saveIfNotExist("name = ?", bean.getName());
        refreshButton();
    }

    private void refreshButton() {
        if (bean.isPoint()) { //已预约
            tvAppoint.setTextColor(getResources().getColor(R.color.color_333333));
            tvAppoint.setBackground(getResources().getDrawable(R.drawable.shape_bg_f2f2f2_4));
        } else {
            tvAppoint.setTextColor(getResources().getColor(R.color.whilt));
            tvAppoint.setBackground(getResources().getDrawable(R.drawable.shape_bg_3853e8_4));
        }
    }

    @OnClick({R.id.imgv_return, R.id.tv_appoint})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgv_return:
                onBackPressed();
                break;
            case R.id.tv_appoint:
                if (bean.isPoint()) {
                    //已预约，点击取消
                    bean.setPoint(false);
                    refreshButton();
                    ToastUtil.showToast(this, "取消预约");
                } else {
                    //未预约，点击预约
                    bean.setPoint(true);
                    refreshButton();
                    DialogUtil.showTipDialog(this, "预约成功", "请及时查看", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    }).show();
                }
                EventBus.getDefault().post(new EventMessage(EventMessage.CLICK_APPOINT, bean));

                //保存到数据库
                ContentValues values = new ContentValues();
                values.put("isPoint", bean.isPoint());
                DataSupport.updateAll(CourseItem.class, values, "name = ?", bean.getName() + "");

                break;
        }
    }
}
