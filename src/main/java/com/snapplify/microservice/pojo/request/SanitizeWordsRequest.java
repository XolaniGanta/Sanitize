package com.snapplify.microservice.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SanitizeWordsRequest {
    private String inputString;
}
