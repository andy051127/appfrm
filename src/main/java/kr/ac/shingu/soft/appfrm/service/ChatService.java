package kr.ac.shingu.soft.appfrm.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import kr.ac.shingu.soft.appfrm.repository.ChatMsg;
import kr.ac.shingu.soft.appfrm.repository.ChatMsgRepository;
import net.minidev.json.JSONObject;

@Service
public class ChatService {
    private final Logger log = LoggerFactory.getLogger(ChatService.class);
    private final Map<String, WebSocketSession> users = new ConcurrentHashMap<>();
    private final ChatMsgRepository chatMsgRepository;

    public ChatService(ChatMsgRepository chatMsgRepository) {
        this.chatMsgRepository = chatMsgRepository;
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
        // 채팅 메세지 저장
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setNickname(nickname);
        chatMsg.setChatContent(msg);
        chatMsg.setChatCreTime(LocalDateTime.now());

        this.chatMsgRepository.save(chatMsg);

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

    // 메세지 로드
    public String loadAllChatMsg() {
        Iterable<ChatMsg> msgList = this.chatMsgRepository.findAll(
                Sort.by(Direction.ASC, "chatCreTime")); //오름 차순, 생성날짜 기준(엔티티에 정의된)
        StringBuilder msg = new StringBuilder(); 
        for (ChatMsg chatMsg : msgList) {
            msg.append("[").append(chatMsg.getNickname()).append("]")
                    .append(chatMsg.getChatContent()).append("\n"); // [userName] 
                                                                        //  메세지 내용 <-- 해당 형식으로 출력
        }
        return msg.toString().trim();
    }
}