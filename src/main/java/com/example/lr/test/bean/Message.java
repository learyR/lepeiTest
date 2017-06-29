package com.example.lr.test.bean;

/**
 * Created by Lr on 2017/6/28.
 */

public class Message {
    private static final int TYPE_SEND = 0;
    private static final int TYPE_RECEIVE = 1;
    String msg;
    String userNick;
    String userAvatorUrl;
    boolean isSend;
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msg='" + msg + '\'' +
                ", userNick='" + userNick + '\'' +
                ", userAvatorUrl='" + userAvatorUrl + '\'' +
                ", isSend=" + isSend +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserAvatorUrl() {
        return userAvatorUrl;
    }

    public void setUserAvatorUrl(String userAvatorUrl) {
        this.userAvatorUrl = userAvatorUrl;
    }
}
