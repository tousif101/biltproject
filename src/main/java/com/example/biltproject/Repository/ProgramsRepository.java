package com.example.biltproject.Repository;

import com.example.biltproject.Objects.Programs;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProgramsRepository extends CrudRepository<Programs, Integer> {
    List<Programs> findProgramsByname(String name);
}
