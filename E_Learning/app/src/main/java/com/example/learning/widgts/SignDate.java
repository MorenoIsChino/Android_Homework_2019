package com.example.learning.widgts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.learning.R;
import com.example.learning.adapters.AdapterDate;
import com.example.learning.adapters.AdapterWeek;
import com.example.learning.interfaces.OnCalendarItemClick;
import com.example.learning.interfaces.OnSignedSuccess;
import com.example.learning.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;



public class SignDate extends LinearLayout {

    private TextView tvYear;
    private InnerGridView gvWeek;
    private InnerGridView gvDate;
    private AdapterDate adapterDate;
    private List<Integer> list_have = new ArrayList<>();

    public SignDate(Context context) {
        super(context);
        init();
    }

    public SignDate(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SignDate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.layout_signdate, this);
        tvYear = view.findViewById(R.id.tvYear);
        gvWeek = view.findViewById(R.id.gvWeek);
        gvDate = view.findViewById(R.id.gvDate);
//        tvYear.setText(DateUtil.getCurrentYearAndMonth());
//        gvWeek.setAdapter(new AdapterWeek(getContext()));
//        adapterDate = new AdapterDate(getContext(), list_have);
//        gvDate.setAdapter(adapterDate);
    }

    public void setHaveNew(List<Integer> list) {
        list_have.clear();
        if (list != null) {
            list_have.addAll(list);
        }

        tvYear.setText(DateUtil.getCurrentYearAndMonth());
        gvWeek.setAdapter(new AdapterWeek(getContext()));
        adapterDate = new AdapterDate(getContext(), list_have);
        gvDate.setAdapter(adapterDate);
    }

    /**
     * 签到成功的回调
     *
     * @param onSignedSuccess
     */
    public void setOnSignedSuccess(OnSignedSuccess onSignedSuccess) {
        adapterDate.setOnSignedSuccess(onSignedSuccess);
    }


    public void setOnItemClick(OnCalendarItemClick onItemClick) {
        adapterDate.setOnItemClick(onItemClick);
    }
}
