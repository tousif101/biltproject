package com.example.biltproject.Service;

import com.example.biltproject.Objects.User;
import com.example.biltproject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService {

    //TODO: ADD Try catch. Make Queries and DB Better.
    //TODO: Make better Primary Keys.

    @Autowired
    UserRepository userRepository;

    //@TODO: add if and try catch logic for user not being in account
    public User fundUser(String username, int amount) {
        List<User> users = userRepository.findByUsername(username);
        User user = users.get(0);
        int val = user.getBiltpoints();
        user.setBiltpoints(val+amount);

        userRepository.save(user);
        return user;
    }


}
