package com.sinaukoding.eventbookingsystem.service.app;

import com.sinaukoding.eventbookingsystem.entity.management_user.User;
import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.request.LoginRequestRecord;

public interface AuthService {

    SimpleMap login(LoginRequestRecord request);

    void logout(User userLoggedIn);

}