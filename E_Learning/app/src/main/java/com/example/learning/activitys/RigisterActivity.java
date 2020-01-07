package com.example.learning.activitys;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.beans.UserBean;
import com.app.shop.mylibrary.utils.DialogUtil;
import com.app.shop.mylibrary.utils.StringUtil;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.example.learning.R;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RigisterActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.inputMobile)
    EditText inputMobile;
    @BindView(R.id.inputpwd)
    EditText inputpwd;
    @BindView(R.id.inputpwd_repeat)
    EditText inputpwdRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigister);
        ButterKnife.bind(this);
        tvTitle.setText("注册");
    }

    @OnClick({R.id.imgv_return, R.id.rigister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgv_return:
                onBackPressed();
                break;
            case R.id.rigister:
                if (StringUtil.isEmpty(inputMobile.getText().toString())) {
                    ToastUtil.showToast(this, "请输入账号");
                    return;
                }

                if (StringUtil.isEmpty(inputpwd.getText().toString())) {
                    ToastUtil.showToast(this, "请输入密码");
                    return;
                }

                if (StringUtil.isEmpty(inputpwdRepeat.getText().toString())) {
                    ToastUtil.showToast(this, "请再次输入密码");
                    return;
                }

                if (!inputpwd.getText().toString().equals(inputpwdRepeat.getText().toString())) {
                    ToastUtil.showToast(this, "两次密码不一致");
                    return;
                }


                UserBean userBean = new UserBean();
                userBean.setName(inputMobile.getText().toString());
                userBean.setPassword(inputpwd.getText().toString());
                userBean.save();

                DialogUtil.showTipDialog(this, "注册成功", "请及时登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).show();
                break;
        }
    }
}
