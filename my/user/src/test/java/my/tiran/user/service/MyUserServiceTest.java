package my.tiran.user.service;

import static org.junit.jupiter.api.Assertions.*;

import my.tiran.user.UserApplication;
import my.tiran.user.common.constant.ErrorType;
import my.tiran.user.common.exception.BuException;
import my.tiran.user.common.util.JwtUtil;
import my.tiran.user.config.H2TestProfileJPAConfig;
import my.tiran.user.controller.AuthApiController;
import my.tiran.user.controller.UserApiController;
import my.tiran.user.mockup.MyMockup;
import my.tiran.user.model.bean.MyUserDetail;
import my.tiran.user.model.dto.*;
import my.tiran.user.model.entity.MyUserEntity;
import my.tiran.user.model.mapper.MyUserMapper;
import my.tiran.user.repository.MyUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = { H2TestProfileJPAConfig.class, UserApplication.class})
public class MyUserServiceTest {
    @Autowired
    private MyMockup myMockup;
    @Autowired
    private UserApiController userApiController;

    @Autowired
    private AuthApiController authApiController;

    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private MyUserMapper myUserMapper;


    @BeforeEach
    public void setUp() throws Exception {
        myMockup.initialize();
    }

    @Test
    void register_success() throws Exception {
        RegisterUser myUser = new RegisterUser();
        myUser.setEmail("test4@example.com");
        myUser.setPassword("password");
        myUser.setUsername("test4");
        myUser.setFirstName("ftest4");
        myUser.setLastName("ltest4");

        ResponseEntity<ResponseAuthentication> response = authApiController.userRegister(myUser);

        MyUserEntity en = myUserRepository.findByUsernameAndActive(myUser.getUsername()).orElseThrow();

        assertTrue(JwtUtil.isTokenValid(response.getBody().getAccessToken(), new MyUserDetail(en)));
    }

    @Test
    void register_duplicate() throws Exception {
        RegisterUser myUser = new RegisterUser();
        myUser.setEmail("test4@example.com");
        myUser.setPassword("password");
        myUser.setUsername("user1");
        myUser.setFirstName("ftest4");
        myUser.setLastName("ltest4");
        BuException exception = assertThrows(BuException.class,() -> authApiController.userRegister(myUser));

        String expectedMessage = ErrorType.B0001.getMessage();
        String actualMessage = exception.getCode().getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void disable_success() throws Exception {
        MyUser myUser = myUserMapper.toMyUser(myUserRepository.findByUsername("user1").get());
        ResponseEntity<DefaultSuccess> response = userApiController.disableMyUser(myUser);

        String expectedMessage = "disable successfully";
        String actualMessage = response.getBody().getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void edit_success() throws Exception {
        MyUser myUser = myUserMapper.toMyUser(myUserRepository.findByUsername("user1").get());

        myUser.setFirstName("fEditUser1");
        ResponseEntity<MyUser> response = userApiController.editMyUser(myUser);

        assertEquals(myUser.getFirstName(), response.getBody().getFirstName());
    }

    @Test
    void get_success() throws Exception {
        MyUser myUser = myUserMapper.toMyUser(myUserRepository.findByUsername("user1").get());
        GetMyUser req = new GetMyUser(myUser.getUsername());
        ResponseEntity<MyUser> response = userApiController.getMyUser(req);

        assertEquals(myUser.getUsername(), response.getBody().getUsername());
    }

    @Test
    void list_success() throws Exception {
        ResponseEntity<MyUsers> myUsers = userApiController.getMyUsers();

        assertFalse(myUsers.getBody().getMyUsers().isEmpty());
    }
}
