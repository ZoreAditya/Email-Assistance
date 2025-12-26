package com.email.mailCreate.pojo;

import lombok.Data;

@Data
public class EmailRequest {
    private String emailContent;
    private String tone;
}
