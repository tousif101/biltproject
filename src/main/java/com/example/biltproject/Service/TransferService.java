package com.example.biltproject.Service;

import com.example.biltproject.Objects.Transfer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransferService {
    List<Transfer> getAllTransfers();
    Transfer executeTransfer(String username, String program, int amount);
}
