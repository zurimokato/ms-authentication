package com.local.ms_authorization.infrastructure.adapters.in.rest.controller.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthRequest {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
}
