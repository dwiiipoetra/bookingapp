package com.sinaukoding.eventbookingsystem.service.app.impl;

import com.sinaukoding.eventbookingsystem.config.UserLoggedInConfig;
import com.sinaukoding.eventbookingsystem.model.app.Checks;
import com.sinaukoding.eventbookingsystem.repository.management_user.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoggedInServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User : " + username + " not found"));
        Checks.isTrue(StringUtils.isNotBlank(user.getToken()), "Session expired, please log in again");
        return new UserLoggedInConfig(user);
    }

}