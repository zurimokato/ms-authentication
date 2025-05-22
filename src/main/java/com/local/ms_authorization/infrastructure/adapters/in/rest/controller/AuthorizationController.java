package com.local.ms_authorization.infrastructure.adapters.in.rest.controller;

import com.local.ms_authorization.application.port.input.AuthenticateUseCase;
import com.local.ms_authorization.application.port.input.RegisterUseCase;
import com.local.ms_authorization.infrastructure.adapters.in.rest.controller.mappers.UserRestMapper;
import com.local.ms_authorization.infrastructure.adapters.in.rest.controller.request.AuthRequest;
import com.local.ms_authorization.infrastructure.adapters.in.rest.controller.response.AuthResponse;
import com.local.ms_authorization.infrastructure.adapters.in.rest.port.AuthorizationApiPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/auth")
public class AuthorizationController implements AuthorizationApiPort {

    private final RegisterUseCase registerUseCase;
    private final AuthenticateUseCase authenticateUseCase;
    private final UserRestMapper userRestMapper;

    @PostMapping(value = "/register")
    public AuthResponse register(@RequestBody AuthRequest authRequest) {
        return userRestMapper.toResponse(registerUseCase.register(userRestMapper.toDomain(authRequest)));
    }

    @PostMapping("/login")
    public AuthResponse auth(@RequestBody AuthRequest authRequest) {
        return userRestMapper.toResponse(authenticateUseCase.authenticate(authRequest.getUsername(), authRequest.getPassword()));
    }


}
