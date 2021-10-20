package com.example.biltproject.Service;

import com.example.biltproject.Objects.Programs;
import com.example.biltproject.Repository.ProgramsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramsService {

    @Autowired
    ProgramsRepository programsRepository;


    @Override
    public List<Programs> getAllPrograms() {
        return (List<Programs>) programsRepository.findAll();
    }


//    @Override
//    public List<Programs> getProgrambyname(String name){
//        return  programsRepository.findProgramsByname(name);
//    }

}
