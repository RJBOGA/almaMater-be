package com.almamater.user.config;

import com.almamater.user.dao.impl.UserDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public UserDaoImpl userDao() {
        return new UserDaoImpl();
    }
}
