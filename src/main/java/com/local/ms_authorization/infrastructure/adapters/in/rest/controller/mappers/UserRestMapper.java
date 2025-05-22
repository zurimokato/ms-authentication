package com.local.ms_authorization.infrastructure.adapters.in.rest.controller.mappers;

import com.local.ms_authorization.domain.model.User;
import com.local.ms_authorization.infrastructure.adapters.in.rest.controller.request.AuthRequest;
import com.local.ms_authorization.infrastructure.adapters.in.rest.controller.response.AuthResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRestMapper {
    AuthResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accessToken", ignore = true)
    @Mapping(target = "refreshToken", ignore = true)
    @Mapping(target = "email",source = "username")
    @Mapping(target = "role",constant = "USER")
    @Mapping(target = "status",expression = "java(com.local.ms_authorization.domain.enums.StatusEnum.ACTIVE)")

    User toDomain(AuthRequest source);
}
