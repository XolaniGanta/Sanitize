package com.snapplify.microservice.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanitizeWordsResponse {
    private String sanitizedWord;
    private List<String> matchedWords;
}
