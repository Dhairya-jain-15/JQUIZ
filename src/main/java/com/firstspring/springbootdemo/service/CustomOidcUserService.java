package com.firstspring.springbootdemo.service;

import com.firstspring.springbootdemo.dao.Userdao;
import com.firstspring.springbootdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOidcUserService extends OidcUserService {

    @Autowired
    private Userdao repository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {

        OidcUser oidcUser = super.loadUser(userRequest);

        System.out.println("OIDC User Service Called");

        String email = oidcUser.getEmail();
        String name = oidcUser.getFullName();
        String picture = oidcUser.getPicture();
        String googleId = oidcUser.getSubject();

        Optional<User> existing = repository.findByEmail(email);

        User user;

        if (existing.isPresent()) {
            user = existing.get();
            user.setName(name);
            user.setPicture(picture);
        } else {
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setPicture(picture);
            user.setGoogleId(googleId);
            user.setProvider("GOOGLE");
        }

        repository.save(user);

        return oidcUser;
    }
}
