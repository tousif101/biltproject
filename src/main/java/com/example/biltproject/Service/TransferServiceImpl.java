package com.example.biltproject.Service;

import com.example.biltproject.Objects.AirlineResponse;
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

    @Autowired
    RestClient restClient;

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
        if(user.getBiltpoints()<totalBP){
            //Change sout to LOGS. Would essentially have the front end not be able to send. But do serverside validation
            System.out.println("USER  DOESNT HAVE ENOUGH POINTS");
            //TODO: return back an error message and response back. Potentially add a comment about the transder. Weather it was succesful or not
        }
        int newBP = userBiltPoint-totalBP;
        user.setBiltpoints(newBP);
        userRepository.save(user);

        //TODO: Add all the try catch logic here.
        AirlineResponse airlineResponse = restClient.readAirLineById("1");
        System.out.println(airlineResponse);

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
