package my.tiran.user.model.mapper;

import java.util.List;
import java.util.Set;
import my.tiran.user.model.dto.MyUser;
import my.tiran.user.model.dto.RegisterUser;
import my.tiran.user.model.entity.MyUserEntity;
import my.tiran.user.model.entity.MyUserRoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(
    componentModel = "spring",
    uses = DateMapper.class
)
public interface MyUserMapper {
    MyUserEntity toMyUserEntity(RegisterUser registerUser);

    @Mapping(target = "roles", source = "myRoles", qualifiedByName = "mapRole")
    MyUser toMyUser(MyUserEntity myUserEntity);
    List<MyUser> toMyUser(List<MyUserEntity> myUserEntityList);

    @Named("mapRole")
    default List<String> mapRole(Set<MyUserRoleEntity> myRoles) {
        return myRoles.stream().map(en -> en.getMyRole().getRoleType().getCode()).toList();
    }
}
