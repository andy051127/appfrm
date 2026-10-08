package kr.ac.shingu.soft.appfrm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import kr.ac.shingu.soft.appfrm.control.WebSocketHandler;

@Configuration //spring boot 실행시 자동으로 바로 실행
@EnableWebSocket //websocket을 활성화
public class WebSocketConfiguration implements WebSocketConfigurer {
    private final Logger log = LoggerFactory.getLogger(WebSocketConfiguration.class);

    public WebSocketConfiguration() {
        this.log.debug("WebSocketConfiguration");
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        this.log.debug("registerWebSocketHandlers");
        registry.addHandler(new WebSocketHandler(), "/chat/ws")
                .setAllowedOrigins("*");
    }
}
