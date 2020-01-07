package com.app.shop.mylibrary.utils;

import android.content.Context;

import com.app.shop.mylibrary.beans.UserBean;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @anthor : 大海
 * 每天进步一点点
 * @time :   2019/12/26
 * @description :
 */


public class UserManager {


    /**
     * 判断用户是否登录
     *
     * @return
     */
    public static boolean isLogin(Context context) {

        String name = SharedPreferencesUtil.getData(context, "user", "name", "");
        Logger.e("---------------username:  " + name);
        return !StringUtil.isEmpty(SharedPreferencesUtil.getData(context, "user", "name", ""));
//        return true;

    }


    public static String getUserName(Context context) {
        return SharedPreferencesUtil.getData(context, "user", "name", "");

    }

    public static boolean isHaveUser(String name) {

        List<UserBean> list = DataSupport.findAll(UserBean.class);
        List<String> list_name = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                list_name.add(list.get(i).getName());
            }
        }
        return list_name.contains(name);
    }


    public static boolean isOk(String name, String pwd) {
        List<UserBean> list = DataSupport.findAll(UserBean.class);
        String password = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) ;
            password = list.get(i).getPassword();
        }
        return password.equals(pwd);
    }

    public void getUser() {
        List<UserBean> list = DataSupport.findAll(UserBean.class);
    }
}
