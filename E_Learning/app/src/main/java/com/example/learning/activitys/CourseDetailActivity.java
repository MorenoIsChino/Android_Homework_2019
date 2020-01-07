package com.example.learning.activitys;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.app.shop.mylibrary.base.BaseActivity;
import com.example.learning.FullScreenVideoView;
import com.example.learning.R;
import com.example.learning.beans.CourseItem;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.video_view)
    FullScreenVideoView videoView;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.tv_play)
    TextView tvPlay;
    @BindView(R.id.tv_pause)
    TextView tvPause;
    @BindView(R.id.tv_stop)
    TextView tvStop;

    boolean isClickStop;
    CourseItem bean;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        ButterKnife.bind(this);
        initData();
        initVideoView();

    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        bean = (CourseItem) bundle.getSerializable("bean");
        tvTitle.setText(bean.getName());
        tvDetail.setText("这里是" + bean.getName() + "的详情");
//        url = bean.getUrl();

        //视频地址自己修改
        url = "android.resource://" + getPackageName() + File.separator + R.raw.video;
    }

    private void initVideoView() {
        if (ContextCompat.checkSelfPermission(CourseDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CourseDetailActivity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        } else {
            initVideoPath(); //初始化VideoView
        }
    }

    private void initVideoPath() {
        videoView.setVideoURI(Uri.parse(url));
        videoView.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoPath();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.imgv_return, R.id.tv_play, R.id.tv_pause, R.id.tv_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgv_return:
                onBackPressed();
            case R.id.tv_play:
                if (isClickStop) {
                    initVideoPath();
                    videoView.start();
                    isClickStop = false;
                } else {

                    if (!videoView.isPlaying()) {
                        videoView.start(); //开始播放
                    }
                }

                break;

            case R.id.tv_pause:
                if (videoView.isPlaying()) {
                    videoView.pause(); //停止播放
                }
                break;

            case R.id.tv_stop:
                if (videoView.isPlaying()) {
                    isClickStop = true;
                    videoView.stopPlayback(); //停止播放
                }

                break;
        }
    }
}
