package com.local.ms_authorization.infrastructure.adapters.out.database;

import com.local.ms_authorization.application.port.ouput.UserOutPutPort;
import com.local.ms_authorization.domain.model.User;
import com.local.ms_authorization.infrastructure.adapters.out.database.mapper.UserEntityMapper;
import com.local.ms_authorization.infrastructure.adapters.out.database.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter  implements UserOutPutPort {

    private final UserEntityMapper userEntityMapper;
    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {

        return userEntityMapper.toDomain( userRepository.findByUsername(username).orElseThrow());
    }

    @Override
    public User save(User user) {
        return userEntityMapper.toDomain(userRepository.save(
                userEntityMapper.toEntity(user)
        ));
    }
}
