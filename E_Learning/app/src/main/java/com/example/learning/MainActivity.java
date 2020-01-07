package com.example.learning;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;

import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.app.shop.mylibrary.utils.UserManager;
import com.app.shop.mylibrary.widgts.ScrollGridView;
import com.example.learning.activitys.CourseCalendarActivity;
import com.example.learning.activitys.CourseCateActivity;
import com.example.learning.activitys.CourseDetailActivity;
import com.example.learning.activitys.LoginActivity;
import com.example.learning.activitys.MyCourseActivity;
import com.example.learning.activitys.SettingActivity;
import com.example.learning.adapters.GridAdapter;
import com.example.learning.beans.ClassBean;
import com.example.learning.beans.CourseItem;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.gv)
    ScrollGridView gv;
    @BindView(R.id.tv_menu)
    TextView tvMenu;
    @BindView(R.id.headImg)
    SimpleDraweeView headImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_my_course)
    TextView tvMyCourse;
    @BindView(R.id.tv_course_appoint)
    TextView tvCourseAppoint;
    @BindView(R.id.tv_note)
    TextView tvNote;
    @BindView(R.id.tv_set)
    TextView tvSet;
    @BindView(R.id.ll_menu)
    LinearLayout llMenu;


    GridAdapter adapter;
    List<CourseItem> list = new ArrayList();
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSwipeEnabled(false);
        ButterKnife.bind(this);
        gv.setFocusable(false);
        gv.setFocusableInTouchMode(false);
        initBanner();
        initLeftMenu();
        initList();
    }

    private void initLeftMenu() {
        tvName.setText(UserManager.isLogin(this) ? UserManager.getUserName(this) : "登陆/注册");
    }

    //列表
    private void initList() {
        initData();
        adapter = new GridAdapter(this, (ArrayList) list, R.layout.item_gv);
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (UserManager.isLogin(MainActivity.this)) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bean", list.get(position));
                    showActivity(MainActivity.this, CourseDetailActivity.class, bundle);
                } else {
                    ToastUtil.showToast(MainActivity.this, "请先登录");
                }
            }
        });
    }

    //本地数据
    private void initData() {
        for (int i = 1; i < 5; i++) {
            CourseItem bean = new CourseItem();
            bean.setId(i);
            bean.setUrl("android.resource://" + getPackageName() + File.separator + R.raw.video);
            switch (i) {
                case 1:
                    bean.setName("Java");
                    bean.setPic(R.mipmap.pic_java);
                    break;
                case 2:
                    bean.setName("Android");
                    bean.setPic(R.mipmap.pic_android);
                    break;
                case 3:
                    bean.setName("IOS");
                    bean.setPic(R.mipmap.pic_ios);
                    break;
                case 4:
                    bean.setName("数据库");
                    bean.setPic(R.mipmap.pic_shujuku);
                    break;
            }
            list.add(bean);
        }
    }

    @Override
    public void onEvent(EventMessage msg) {
        super.onEvent(msg);
        if (msg.getMessageType() == EventMessage.LOGIN) {
            initLeftMenu();
        } else if (msg.getMessageType() == EventMessage.LOGOUT) {
            initLeftMenu();
        }
    }

    /**
     * 轮播图
     */
    private void initBanner() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new FresscoImageLoader());
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置允许手势滑动
        banner.setViewPagerIsScroll(true);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        List list_banner = new ArrayList();
        list_banner.add(R.mipmap.pic_java);
        list_banner.add(R.mipmap.pic_android);
        list_banner.add(R.mipmap.pic_ios);
        list_banner.add(R.mipmap.pic_shujuku);
        banner.setImages(list_banner);

        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //测试存数据

            }
        });
    }

    @OnClick({R.id.tv_menu, R.id.headImg, R.id.tv_my_course, R.id.tv_course_appoint, R.id.tv_note, R.id.tv_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.headImg:
                if (!UserManager.isLogin(this)) {
                    showActivity(this, LoginActivity.class);
                }
                drawerLayout.closeDrawers();
                break;
            case R.id.tv_my_course:
                if (UserManager.isLogin(this)) {
                    showActivity(this, MyCourseActivity.class);
                    drawerLayout.closeDrawers();
                } else {
                    ToastUtil.showToast(this, "请先登录");
                }
                break;
            case R.id.tv_course_appoint:
                if (UserManager.isLogin(this)) {
                    showActivity(this, CourseCateActivity.class);
                    drawerLayout.closeDrawers();
                } else {
                    ToastUtil.showToast(this, "请先登录");
                }
                break;
            case R.id.tv_note:
                if (UserManager.isLogin(this)) {
                    showActivity(this, CourseCalendarActivity.class);
                    drawerLayout.closeDrawers();
                } else {
                    ToastUtil.showToast(this, "请先登录");
                }
                break;
            case R.id.tv_set:
                if (UserManager.isLogin(this)) {
                    showActivity(this, SettingActivity.class);
                    drawerLayout.closeDrawers();
                } else {
                    ToastUtil.showToast(this, "请先登录");
                }
                break;
        }
    }

    public class FresscoImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            int url = (int) path;
            imageView.setImageResource(url);
        }

        //提供createImageView 方法，方便fresco自定义ImageView
        @Override
        public ImageView createImageView(Context context) {

            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) getLayoutInflater().inflate(R.layout.layout_banner_imageview, null);
//            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
            return simpleDraweeView;
        }
    }
}
