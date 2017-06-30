package com.example.lr.test.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Lr on 2017/6/28.
 */
@Entity
public class Message {
    private static final int TYPE_SEND = 0;
    private static final int TYPE_RECEIVE = 1;
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String msg;
    private String userNick;
    private String userAvatorUrl;
    private boolean isSend;
    private String time;

    @Generated(hash = 589978558)
    public Message(Long id, @NotNull String msg, String userNick,
            String userAvatorUrl, boolean isSend, String time) {
        this.id = id;
        this.msg = msg;
        this.userNick = userNick;
        this.userAvatorUrl = userAvatorUrl;
        this.isSend = isSend;
        this.time = time;
    }

    @Generated(hash = 637306882)
    public Message() {
    }

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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsSend() {
        return this.isSend;
    }

    public void setIsSend(boolean isSend) {
        this.isSend = isSend;
    }
}
