package my.tiran.user.service;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.tiran.user.common.constant.ActiveType;
import my.tiran.user.common.constant.ErrorType;
import my.tiran.user.common.exception.BuException;
import my.tiran.user.model.dto.GetMyUser;
import my.tiran.user.model.dto.MyUser;
import my.tiran.user.model.entity.MyUserEntity;
import my.tiran.user.model.mapper.MyUserMapper;
import my.tiran.user.repository.MyUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MyUserService {
    private final MyUserRepository myUserRepository;

    private final MyUserMapper myUserMapper;

    @Transactional
    public MyUser updateUser(@Valid MyUser myUser) throws BuException {
        Optional<MyUserEntity> userEn = myUserRepository.findByUsername(myUser.getUsername());
        if (userEn.isEmpty()) throw new BuException(ErrorType.B0002);
        MyUserEntity user = userEn.get();
        user.setFirstName(myUser.getFirstName());
        user.setLastName(myUser.getLastName());
        myUserRepository.save(user);

        return myUserMapper.toMyUser(user);
    }

    @Transactional
    public void disableUser(@Valid MyUser myUser) throws BuException {
        MyUserEntity user = myUserRepository.findByUsername(myUser.getUsername())
                .orElseThrow(() -> new BuException(ErrorType.B0002));
        user.setActive(ActiveType.INACTIVE);
        myUserRepository.save(user);
    }

    public MyUser getMyUser(@Valid GetMyUser req) throws BuException {
        MyUserEntity userEn = myUserRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new BuException(ErrorType.B0002));

        return myUserMapper.toMyUser(userEn);
    }

    public List<MyUser> getMyUsers() {
        return myUserMapper.toMyUser(myUserRepository.findAllByActive(ActiveType.ACTIVE));
    }
}
