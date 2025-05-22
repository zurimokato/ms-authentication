package com.local.ms_authorization.infrastructure.adapters.in.rest.controller.response;

import com.local.ms_authorization.domain.enums.StatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthResponse {
    private String id;
    private String email;
    private String role;
    private String accessToken;
    private String refreshToken;
    private StatusEnum status;

}
