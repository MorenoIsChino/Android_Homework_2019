package com.example.learning.activitys;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.beans.UserBean;
import com.app.shop.mylibrary.utils.SharedPreferencesUtil;
import com.app.shop.mylibrary.utils.StringUtil;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.app.shop.mylibrary.utils.UserManager;
import com.example.learning.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.inputMobile)
    EditText inputMobile;
    @BindView(R.id.inputpwd)
    EditText inputpwd;
    @BindView(R.id.eyeOpen)
    ImageView eyeOpen;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private boolean isEyesOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        tvTitle.setText("登录");
        inputpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());//默认隐藏密码
    }

    @OnClick({R.id.imgv_return, R.id.rigister, R.id.toLogin, R.id.eyeOpen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgv_return:
                onBackPressed();
                break;

            case R.id.eyeOpen:
                if (isEyesOpen) {
                    inputpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏密码
                    eyeOpen.setImageResource(R.mipmap.input_edittext_eye_close);
                    inputpwd.setSelection(inputpwd.getText().toString().length());
                    isEyesOpen = false;
                } else {
                    isEyesOpen = true;
                    eyeOpen.setImageResource(R.mipmap.input_edittext_eye_open);
                    inputpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//显示密码
                    inputpwd.setSelection(inputpwd.getText().toString().length());
                }
                break;
            case R.id.rigister:
                showActivity(this, RigisterActivity.class);
                break;
            case R.id.toLogin:
                if (StringUtil.isEmpty(inputMobile.getText().toString())) {
                    ToastUtil.showToast(this, "请输入账号");
                    return;
                }

                if (StringUtil.isEmpty(inputpwd.getText().toString())) {
                    ToastUtil.showToast(this, "请输入密码");
                    return;
                }

                //是不是有这个人
                boolean isHave = UserManager.isHaveUser(inputMobile.getText().toString());
                if (isHave) {
                    if (UserManager.isOk(inputMobile.getText().toString(), inputpwd.getText().toString())) {
                        UserBean userBean = new UserBean();
                        userBean.setName(inputMobile.getText().toString());
                        SharedPreferencesUtil.saveDataBean(this, userBean, "user");
                        EventBus.getDefault().post(new EventMessage(EventMessage.LOGIN));
                        ToastUtil.showToast(this, "登录成功");
                        finish();
                    } else {
                        ToastUtil.showToast(this, "账号密码不匹配");
                        tvTips.setText("账号密码不匹配");
                    }
                } else {
                    ToastUtil.showToast(this, "请先注册");
                    tvTips.setText("请先注册");
                }
                break;
        }
    }
}
