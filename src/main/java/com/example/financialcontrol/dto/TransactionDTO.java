package com.example.financialcontrol.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDTO {
        private double value;
        private Date date;
        private String description;
        private String category;
    }
