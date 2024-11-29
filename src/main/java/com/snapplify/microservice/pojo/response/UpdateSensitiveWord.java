package com.snapplify.microservice.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateSensitiveWord {
    private Long id;
    private String oldWord;
    private String newWord;
}
