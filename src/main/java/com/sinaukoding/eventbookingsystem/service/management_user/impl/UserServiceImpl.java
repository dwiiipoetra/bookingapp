package com.sinaukoding.eventbookingsystem.service.management_user.impl;

import com.sinaukoding.eventbookingsystem.builder.CustomBuilder;
import com.sinaukoding.eventbookingsystem.entity.management_user.User;
import com.sinaukoding.eventbookingsystem.mapper.management_user.UserMapper;
import com.sinaukoding.eventbookingsystem.model.app.AppPage;
import com.sinaukoding.eventbookingsystem.model.app.SimpleMap;
import com.sinaukoding.eventbookingsystem.model.filter.UserFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.UserRequestRecord;
import com.sinaukoding.eventbookingsystem.repository.management_user.UserRepository;
import com.sinaukoding.eventbookingsystem.service.management_user.UserService;
import com.sinaukoding.eventbookingsystem.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void add(UserRequestRecord request) {
        validateMandatory(request);
        if (userRepository.existsByEmail(request.email().toLowerCase())) {
            throw new RuntimeException("Email ["+request.email()+"] has been used");
        }
        if (userRepository.existsByUsername(request.username().toLowerCase())) {
            throw new RuntimeException("Username [" + request.username() + "] has been used");
        }

        var user = userMapper.requestToEntity(request);
        userRepository.save(user);
    }

    @Override
    public void edit(UserRequestRecord request) {
        validateMandatory(request);

        var userExisting = userRepository.findById(request.id()).orElseThrow(()->new RuntimeException("User data not found"));

        if (userRepository.existsByEmailAndIdNot(request.email().toLowerCase(), request.id())) {
            throw new RuntimeException("Email ["+request.email()+"] has been used");
        }
        if (userRepository.existsByUsernameAndIdNot(request.username().toLowerCase(), request.id())) {
            throw new RuntimeException("Username [" + request.username() + "] has been used");
        }

        var user = userMapper.requestToEntity(request);
        user.setId(userExisting.getId());
        userRepository.save(user);
    }

    @Override
    public Page<SimpleMap> findAll(UserFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<User> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("name", filterRequest.name(), builder);
        FilterUtil.builderConditionNotBlankLike("email", filterRequest.email(), builder);
        FilterUtil.builderConditionNotBlankLike("phone", filterRequest.phone(), builder);
        FilterUtil.builderConditionNotNullEqual("status", filterRequest.status(), builder);
        FilterUtil.builderConditionNotNullEqual("role", filterRequest.role(), builder);

        Page<User> listUser = userRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listUser.stream().map(user -> {
            SimpleMap data = new SimpleMap();
            data.put("id", user.getId());
            data.put("name", user.getName());
            data.put("email", user.getEmail());
            data.put("phone", user.getPhone());
            data.put("role", user.getRole().getLabel());
            data.put("status", user.getStatus().getLabel());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listUser.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User data not found"));
        SimpleMap data = new SimpleMap();
        data.put("id", user.getId());
        data.put("name", user.getName());
        data.put("email", user.getEmail());
        data.put("phone", user.getPhone());
        data.put("role", user.getRole().getLabel());
        data.put("status", user.getStatus().getLabel());
        return data;
    }

    private void validateMandatory(UserRequestRecord request) {
        if (request.name() == null || request.name().isEmpty()) {
            throw new RuntimeException("Name cannot be empty");
        }
        if (request.email() == null || request.email().isEmpty()) {
            throw new RuntimeException("Email cannot be empty");
        }
        if (request.phone() == null || request.phone().isEmpty()) {
            throw new RuntimeException("Phone cannot be empty");
        }
        if (request.password() == null || request.password().isEmpty()) {
            throw new RuntimeException("Password cannot be empty");
        }
        if (request.status() == null) {
            throw new RuntimeException("Status cannot be empty");
        }
        if (request.role() == null) {
            throw new RuntimeException("Role cannot be empty");
        }
    }
}