package com.local.ms_authorization.domain.services;

import com.local.ms_authorization.application.port.input.AuthenticateUseCase;
import com.local.ms_authorization.application.port.input.RegisterUseCase;
import com.local.ms_authorization.application.port.ouput.UserOutPutPort;
import com.local.ms_authorization.domain.enums.RoleEnum;
import com.local.ms_authorization.domain.enums.StatusEnum;
import com.local.ms_authorization.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorizationService implements RegisterUseCase , AuthenticateUseCase {

    private final UserOutPutPort userOutPutPort;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public User register(User userRequest) {
        try {
            userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

            var user = User.builder()
                    .email(userRequest.getEmail())
                    .password(userRequest.getPassword())
                    .role(RoleEnum.USER.name())
                    .status(StatusEnum.ACTIVE)
                    .build();
            var u = userOutPutPort.save(user);
            var jwt = jwtService.generateToken(user);
            return User.builder()
                    .accessToken(jwt).id(u.getId())
                    .email(u.getEmail())
                    .lastname(u.getLastname())
                    .firstname(u.getFirstname())
                    .role(u.getRole())
                    .status(u.getStatus())
                    .build();
        }catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }


    }

    @Override
    public User authenticate(String username, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        var u=userOutPutPort.findByUsername(username);
        var jwt = jwtService.generateToken(u);

        return User.builder()
                .accessToken(jwt).id(u.getId())
                .email(u.getEmail())
                .lastname(u.getLastname())
                .firstname(u.getFirstname())
                .role(u.getRole())
                .status(u.getStatus())
                .build();
    }
}
