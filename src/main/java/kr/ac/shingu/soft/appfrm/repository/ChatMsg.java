package kr.ac.shingu.soft.appfrm.repository;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_msg")
public class ChatMsg {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "msg_id", nullable = false)
    private long msgId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "chat_content", nullable = false)
    private String chatContent;

    @Column(name = "chat_cre_time", nullable = false)
    private LocalDateTime chatCreTime;

    public ChatMsg() {
    }

    public ChatMsg(long msgId, String nickname, String chatContent, LocalDateTime chatCreTime) {
        this.msgId = msgId;
        this.nickname = nickname;
        this.chatContent = chatContent;
        this.chatCreTime = chatCreTime;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public LocalDateTime getChatCreTime() {
        return chatCreTime;
    }

    public void setChatCreTime(LocalDateTime chatCreTime) {
        this.chatCreTime = chatCreTime;
    }

    @Override
    public String toString() {
        return "ChatMsg [msgId=" + msgId + ", nickname=" + nickname + ", chatContent=" + chatContent + ", chatCreTime="
                + chatCreTime + "]";
    }

    
}