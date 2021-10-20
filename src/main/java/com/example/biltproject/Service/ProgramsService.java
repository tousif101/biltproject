package com.example.biltproject.Service;

import com.example.biltproject.Objects.Programs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProgramsService {
     List<Programs> getAllPrograms();
    //List<Programs> getProgrambyname(String name);
}
