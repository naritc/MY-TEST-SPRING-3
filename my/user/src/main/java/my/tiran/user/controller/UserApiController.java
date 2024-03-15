package my.tiran.user.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.tiran.user.common.util.ResponseUtil;
import my.tiran.user.model.dto.*;
import my.tiran.user.service.MyUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Log4j2
@RequiredArgsConstructor
@Controller
public class UserApiController implements UserApi {
    private final MyUserService myUserService;

    @Override
    public ResponseEntity<DefaultSuccess> disableMyUser(MyUser myUser) throws Exception {
        myUserService.disableUser(myUser);
        return ResponseUtil.success("Disable Successfully");
    }

    @Override
    public ResponseEntity<MyUser> editMyUser(MyUser myUser) throws Exception {
        myUserService.updateUser(myUser);
        return ResponseUtil.success("Edit Successfully");
    }

    @Override
    public ResponseEntity<MyUser> getMyUser(GetMyUser getMyUser) throws Exception {
        MyUser resp = myUserService.getMyUser(getMyUser);
        return ResponseUtil.success(resp);
    }

    @Override
    public ResponseEntity<MyUsers> getMyUsers() throws Exception {
        List<MyUser> result = myUserService.getMyUsers();
        MyUsers resp = MyUsers.builder().myUsers(result).build();
        return ResponseUtil.success(resp);
    }
}
