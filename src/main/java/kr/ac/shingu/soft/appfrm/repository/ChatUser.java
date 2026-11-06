package kr.ac.shingu.soft.appfrm.repository;

import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_user")
public class ChatUser {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "user_pwd", nullable = false)
    private String userPwd;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "user_mail", nullable = false)
    private String userMail;

    public ChatUser() {
    }

    public ChatUser(String userId, String userPwd, String nickname, String userMail) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.nickname = nickname;
        this.userMail = userMail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Override
    public String toString() {
        return "ChatUser [userId=" + userId
                + ", userPwd=" + userPwd
                + ", nickname=" + nickname
                + ", userMail=" + userMail + "]";
    }
}