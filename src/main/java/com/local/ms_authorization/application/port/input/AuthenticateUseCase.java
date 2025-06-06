package com.local.ms_authorization.application.port.input;

import com.local.ms_authorization.domain.model.User;

public interface AuthenticateUseCase {
    User authenticate(String username, String password);
}
