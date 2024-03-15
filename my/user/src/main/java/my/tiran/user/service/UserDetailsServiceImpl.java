package my.tiran.user.service;


import my.tiran.user.model.bean.MyUserDetail;
import my.tiran.user.model.entity.MyUserEntity;
import my.tiran.user.repository.MyUserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    MyUserRepository myUserRepository;

    @Override
    public MyUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserEntity user = myUserRepository.findByUsernameAndActive(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new MyUserDetail(user);
    }
}
