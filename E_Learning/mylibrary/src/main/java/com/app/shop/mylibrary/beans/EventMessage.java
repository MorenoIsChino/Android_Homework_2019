package com.app.shop.mylibrary.beans;


public class EventMessage {

    private Object mObject;

    private int messageType;

    public EventMessage(int type) {
        messageType = type;
    }

    public EventMessage(int type, Object object) {
        mObject = object;
        messageType = type;
    }

    public Object getmObject() {
        return mObject;
    }

    public int getMessageType() {
        return messageType;
    }


    //消息类型
    public static final int CLICK_LIKE = 1;//
    public static final int CLICK_APPOINT = 2;//预约
    public static final int LOGIN = 3;//登陆
    public static final int LOGOUT = 2;//退出


}
