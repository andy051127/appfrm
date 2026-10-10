package kr.ac.shingu.soft.appfrm.service;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import net.minidev.json.JSONObject;

@Service
public class ChatService {
    private final Logger log = LoggerFactory.getLogger(ChatService.class);
    private final Map<String, WebSocketSession> users = new ConcurrentHashMap<>();

    public ChatService() {
    }

    public void addUser(String nickname, WebSocketSession session) {
        this.log.debug("New Chat User Nickname: " + nickname);
        this.users.put(nickname, session);
        this.addMessage(nickname, "입장하셨습니다.");
    }

    public void removeUser(String nickname) {
        this.users.remove(nickname);
        this.addMessage(nickname, "방을 나갔습니다.");
    }

    public void addMessage(String nickname, String msg) {
        JSONObject obj = new JSONObject();
        obj.put("cmd", "msg");
        obj.put("nickname", nickname);
        obj.put("msg", msg);
        TextMessage message = new TextMessage(obj.toJSONString());
        Set<Entry<String, WebSocketSession>> entrySet = this.users.entrySet();
        for (Entry<String, WebSocketSession> entry : entrySet) {
            WebSocketSession session = entry.getValue();
            try {
                session.sendMessage(message);
                this.log.debug(entry.getKey() + "sended");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}