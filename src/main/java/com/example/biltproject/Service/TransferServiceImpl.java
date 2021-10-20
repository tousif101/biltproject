package com.example.biltproject.Service;

import com.example.biltproject.Objects.Programs;
import com.example.biltproject.Objects.Transfer;
import com.example.biltproject.Objects.User;
import com.example.biltproject.Repository.ProgramsRepository;
import com.example.biltproject.Repository.TransferRepository;
import com.example.biltproject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProgramsRepository programsRepository;

//    @Autowired
//    ProgramsService programsService;

    @Override
    public List<Transfer> getAllTransfers() {
        return (List<Transfer>) transferRepository.findAll();
    }

    @Override
    public Transfer executeTransfer(String username, String program, int amount){
        User user = userRepository.findByUsername(username).get(0);
        int userBiltPoint = user.getBiltpoints();

        Programs dbProgram = programsRepository.findProgramsByname(program).get(0);
        int cost = dbProgram.getCost();

        int totalBP = amount * cost;
        //TODO: Check userbiltPoint Logic here
        //TODO: Make some calls ASYNC
        int newBP = userBiltPoint-totalBP;
        user.setBiltpoints(newBP);
        userRepository.save(user);

        //TODO: Call API HERE
        //Feign Client

        Transfer transfer = new Transfer();
        transfer.setAmount(amount);
        transfer.setCost(totalBP);
        transfer.setUser(username);
        transfer.setDate(LocalDateTime.now().toString());
        transfer.setProgram(program);

        transferRepository.save(transfer);

        return transfer;

    }


}
