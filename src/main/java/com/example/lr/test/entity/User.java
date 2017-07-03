package com.example.lr.test.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Lr on 2017/6/30.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String userName;
    private String userNick;
    private String AvatarUri;
    @Generated(hash = 2068412805)
    public User(Long id, @NotNull String userName, String userNick,
            String AvatarUri) {
        this.id = id;
        this.userName = userName;
        this.userNick = userNick;
        this.AvatarUri = AvatarUri;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserNick() {
        return this.userNick;
    }
    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }
    public String getAvatarUri() {
        return this.AvatarUri;
    }
    public void setAvatarUri(String AvatarUri) {
        this.AvatarUri = AvatarUri;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
   

}
