package com.local.ms_authorization.application.port.ouput;

import com.local.ms_authorization.domain.model.User;

public interface UserOutPutPort {
    User findByUsername(String username);
    User save(User user);

}
