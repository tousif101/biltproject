package com.example.biltproject.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {
    private String username;
    private int amount;
    private String program;
}
