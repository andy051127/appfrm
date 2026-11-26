package kr.ac.shingu.soft.appfrm.control;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.ac.shingu.soft.appfrm.repository.ChatUser;
import kr.ac.shingu.soft.appfrm.repository.ChatUserRepository;
import net.minidev.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginRestControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ChatUserRepository chatUserRepository;

    protected MockMvc mockMvc;

    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @DisplayName("Login Test")
    @Test
    void testLogin() throws Exception {
        // given
        final String url = "/login";
        Optional<ChatUser> optUser = this.chatUserRepository.findById("user1");
        Assertions.assertTrue(optUser.isPresent());
        ChatUser user = optUser.get();

        JSONObject request = new JSONObject();
        request.put("userId", user.getUserId());
        request.put("userPwd", user.getUserPwd());

        // when
        final ResultActions result = this.mockMvc.perform(
                post(url).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request.toJSONString()));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
