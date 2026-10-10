package kr.ac.shingu.soft.appfrm.control;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import kr.ac.shingu.soft.appfrm.service.ChatService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public class WebSocketHandler extends TextWebSocketHandler {
    private final Logger log = LoggerFactory.getLogger(WebSocketHandler.class);
    private final ChatService chatService;

    public WebSocketHandler(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        String payload = message.getPayload();
        JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
        JSONObject jsonObject = (JSONObject) parser.parse(payload);
        String cmd = jsonObject.getAsString("cmd");
        String nickname = jsonObject.getAsString("nickname");
        switch (cmd) {
            case "poll": {
                this.chatService.addUser(nickname, session);
                Map<String, Object> attr = session.getAttributes();
                attr.put("nickname", nickname);
                break;
            }
            case "push": {
                String msg = jsonObject.getAsString("msg");
                this.chatService.addMessage(nickname, msg);
                break;
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
            throws Exception {
        Map<String, Object> attr = session.getAttributes();
        String nickname = (String) attr.get("nickname");
        this.log.debug("WebSocket Closed!! Nickname: " + nickname);
        this.chatService.removeUser(nickname);
    }
}
