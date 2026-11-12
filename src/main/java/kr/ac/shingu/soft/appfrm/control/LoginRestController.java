package kr.ac.shingu.soft.appfrm.control;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.ac.shingu.soft.appfrm.data.LoginData;
import kr.ac.shingu.soft.appfrm.data.ResponseData;
import kr.ac.shingu.soft.appfrm.repository.ChatUser;
import kr.ac.shingu.soft.appfrm.repository.ChatUserRepository;

@RestController
public class LoginRestController {
    private final Logger log = LoggerFactory.getLogger(LoginRestController.class);
    private final ChatUserRepository chatUserRepository;

    public LoginRestController(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    @PostMapping(path = "/login", consumes = { "application/json" })
    public ResponseData login(@RequestBody LoginData loginData, HttpServletRequest request) throws Exception {
        this.log.debug("" + loginData);
        String userId = loginData.getUserId();
        String userPwd = loginData.getUserPwd();

        Optional<ChatUser> chatUserOpt = this.chatUserRepository.findById(userId);
        if (chatUserOpt.isEmpty()) {
            return new ResponseData("ID나 패스워드가 잘못되었습니다.", false);
        }

        ChatUser chatUser = chatUserOpt.get();
        if (chatUser.getUserPwd().equals(userPwd) == false) {
            return new ResponseData("ID나 패스워드가 잘못되었습니다.", false);
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        session = request.getSession(true);
        session.setAttribute("userId", userId);
        return new ResponseData("로그인 되었습니다.", true);
    }
}
