package my.tiran.user.mockup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import my.tiran.user.model.entity.MyUserEntity;
import my.tiran.user.model.entity.MyUserRoleEntity;
import my.tiran.user.model.entity.RoleEntity;
import my.tiran.user.repository.MyUserRepository;
import my.tiran.user.repository.MyUserRolesRepository;
import my.tiran.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@Component
public class MyMockup {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private MyUserRolesRepository myUserRolesRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void initialize() throws Exception {
        createRole();
        createMyUser();
        createUserRole();
    }

    private void createMyUser() throws Exception {
        File file = ResourceUtils.getFile("classpath:test/my_user.json");
        List<MyUserEntity> entities = Arrays.asList(objectMapper.readValue(file, MyUserEntity[].class));
        myUserRepository.saveAllAndFlush(entities);
    }

    private void createRole() throws Exception {
        File file = ResourceUtils.getFile("classpath:test/role.json");
        List<RoleEntity> entities = Arrays.asList(objectMapper.readValue(file, RoleEntity[].class));
        roleRepository.saveAllAndFlush(entities);
    }

    private void createUserRole() throws Exception {
        File file = ResourceUtils.getFile("classpath:test/my_user_role.json");
        List<MyUserRoleEntity> entities = Arrays.asList(objectMapper.readValue(file, MyUserRoleEntity[].class));
        myUserRolesRepository.saveAllAndFlush(entities);
    }


}
