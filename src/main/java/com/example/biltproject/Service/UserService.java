package com.example.biltproject.Service;

import com.example.biltproject.Objects.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User fundUser(String username, int amount);

}
