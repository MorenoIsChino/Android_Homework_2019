package com.example.learning.adapters;

import android.content.Context;
import android.widget.TextView;

import com.app.shop.mylibrary.base.CommonAdapter;
import com.app.shop.mylibrary.base.ViewHolder;
import com.example.learning.R;
import com.example.learning.beans.CourseItem;
import com.example.learning.beans.MyCourseBean;

import java.util.ArrayList;

public class CourseItemListAdapter extends CommonAdapter {
    public CourseItemListAdapter(Context context, ArrayList datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void setView(ViewHolder holder, Object o, int position) {

        CourseItem bean = (CourseItem) o;

        TextView tv_name = holder.getView(R.id.tv_course_name);
        tv_name.setText(bean.getName());
    }
}
