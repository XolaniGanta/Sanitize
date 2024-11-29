package com.snapplify.microservice.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSensitiveWords {
    private Integer id;
    private String  newWord;
}
