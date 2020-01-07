package com.example.learning.adapters;

import android.content.Context;
import android.widget.TextView;

import com.app.shop.mylibrary.base.CommonAdapter;
import com.app.shop.mylibrary.base.ViewHolder;
import com.example.learning.R;
import com.example.learning.beans.ClassBean;
import com.example.learning.beans.CourseItem;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;



public class GridAdapter extends CommonAdapter {

    public GridAdapter(Context context, ArrayList datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void setView(ViewHolder holder, Object o, int position) {
        CourseItem bean = (CourseItem) o;
        SimpleDraweeView imgv_Pic = holder.getView(R.id.imgv_home_gv);
        TextView textView = holder.getView(R.id.tv_home_gv);

        imgv_Pic.setActualImageResource(bean.getPic());
        textView.setText(bean.getName());
    }
}
