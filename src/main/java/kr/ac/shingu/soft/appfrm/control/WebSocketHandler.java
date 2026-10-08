package kr.ac.shingu.soft.appfrm.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {
    private final Logger log = LoggerFactory.getLogger(WebSocketHandler.class);

    public WebSocketHandler() {
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        String payload = message.getPayload();
        this.log.debug("received message: " + payload);
    }
}