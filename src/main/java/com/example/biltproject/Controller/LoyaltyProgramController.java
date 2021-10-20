package com.example.biltproject.Controller;

import com.example.biltproject.Objects.*;
import com.example.biltproject.Service.ProgramsService;
import com.example.biltproject.Service.TransferService;
import com.example.biltproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;




@RestController
public class LoyaltyProgramController {

    public static int amount = 0;

    @Autowired
    ProgramsService programsService;

    @Autowired
    UserService userService;

    @Autowired
    TransferService transferService;

    @GetMapping("/programs")
    public List<Programs> getPrograms() {
        //TODO: On Application startup. Cache the programs in memory. Reference Cache! Instead of DB Queries.
        return programsService.getAllPrograms();
    }

    @PostMapping(value = "/fundAccount",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User fundAccount(@RequestBody FundRequest fundRequest){

        //TODO: Find Account in DB, Add it to the account, and then persist it back
        amount += fundRequest.getAmount();

        User user = userService.fundUser(fundRequest.getUsername(),fundRequest.getAmount());
        return user;
    }

    //TODO: Check if the amount transferred is proper
    @PostMapping(value = "/transfer",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Transfer transferPoints(@RequestBody TransferRequest transferRequest){
        String programName = transferRequest.getProgram(); //find the program. Get the item.
        int amountOfProgramPoints = transferRequest.getAmount(); //50 Delta Points, at 2 a peace. Cost is 100Bilt Point
        String user = transferRequest.getUsername(); // find the user.

        //create the transfer object, and persist it.

        return transferService.executeTransfer(user,programName,amountOfProgramPoints); // Transfer detail.
    }

    //TODO: Turn this into a post. or Get for each user. Turn users in accountRefIds
    @GetMapping(value = "/transfer")
    public List<Transfer> transferPoints(){

        return transferService.getAllTransfers(); // Transfer detail.
    }

}
