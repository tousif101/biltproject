package com.example.biltproject.Service;

import com.example.biltproject.Objects.AirlineResponse;
import com.example.biltproject.Objects.Programs;
import com.example.biltproject.Objects.Transfer;
import com.example.biltproject.Objects.User;
import com.example.biltproject.Repository.ProgramsRepository;
import com.example.biltproject.Repository.TransferRepository;
import com.example.biltproject.Repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class TransferServiceTest {

    @InjectMocks
    TransferServiceImpl transferService;

    @Mock
    UserRepository userRepository;

    @Mock
    ProgramsRepository programsRepository;

    @Mock
    RestClient restClient;

    @Mock
    TransferRepository transferRepository;

    /*
     TODO:
     @Rule
     WireMock server = new WireMock(8443);
     */


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHappyPathTransfer(){

        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setBiltpoints(100);
        user.setUsername("tousif101");
        userList.add(user);
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(userList);

        List<Programs> programList = new ArrayList<>();
        Programs programs = new Programs();
        programs.setCost(2);
        programs.setName("Delta");
        programList.add(programs);
        Mockito.when(programsRepository.findProgramsByname(Mockito.anyString())).thenReturn(programList);

        //Mock the webserver. Standup a Wiremock server later.
        AirlineResponse airlineResponse = new AirlineResponse();
        airlineResponse.setName("test");
        Mockito.when(restClient.readAirLineById(Mockito.anyString())).thenReturn(airlineResponse);

        Transfer transfer1 = new Transfer();
        transfer1.setAmount(50);
        transfer1.setProgram("Test");
        Mockito.when(transferRepository.save(Mockito.anyObject())).thenReturn(transfer1);

        Transfer transfer = transferService.executeTransfer("tousif101","Dellta",50);

        assertEquals(transfer.getCost(),100);
    }
}
