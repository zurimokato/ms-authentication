package com.local.ms_authorization.infrastructure.adapters.out.database.mapper;

import com.local.ms_authorization.domain.model.User;
import com.local.ms_authorization.infrastructure.adapters.out.database.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    @Mapping(ignore = true, target = "password")
    @Mapping(ignore = true,target = "authorities")
    @Mapping(target = "email", source = "username")
    @Mapping(target = "role", source = "userRol")
    User toDomain(UserEntity source);

    @Mapping(ignore = true,target = "authorities")
    @Mapping(target = "userRol",constant = "USER")
    UserEntity toEntity(User source);

}
