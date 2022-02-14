package com.antsiferov.thirdtask.services;

import com.antsiferov.thirdtask.entity.CustomUser;
import com.antsiferov.thirdtask.entity.Users;
import com.antsiferov.thirdtask.interfaces.UsersInterface;
import com.antsiferov.thirdtask.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UsersService implements UsersInterface, ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByName(username)
                .map(user -> new CustomUser(user))
                .orElse(null);
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        updateLastLogindate(username);
    }


    private void updateLastLogindate(String username) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        usersRepository.setLastDate(df.format(new Date()), username);
    }
}
