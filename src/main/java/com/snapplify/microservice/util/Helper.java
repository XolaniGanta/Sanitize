package com.snapplify.microservice.util;

import com.snapplify.microservice.entity.SensitiveWords;
import com.snapplify.microservice.pojo.request.SensitiveWordsRequest;
import com.snapplify.microservice.pojo.response.GetSensitiveWordsResponse;
import com.snapplify.microservice.pojo.response.SanitizeWordsResponse;
import com.snapplify.microservice.pojo.response.SensitiveWordsResponse;
import com.snapplify.microservice.pojo.response.UpdateSensitiveWord;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class Helper {

    public static SensitiveWordsResponse buildSensitiveWordResponse(SensitiveWordsRequest sensitiveWordsRequest) {
        return SensitiveWordsResponse.builder()
                .numOfWordsSaved(sensitiveWordsRequest.getWords().size())
                .build();
    }

    public static SanitizeWordsResponse buildSanitizeWordsResponse(String sanitized, List<String> matchedWords) {
        return SanitizeWordsResponse.builder()
                .sanitizedWord(sanitized)
                .matchedWords(matchedWords)
                .build();
    }

    public static GetSensitiveWordsResponse buildGetSensituveWordsResponse(List<String> sensitiveWords){
        return GetSensitiveWordsResponse.builder()
                .sensitiveWords(sensitiveWords)
                .build();
    }

    public static UpdateSensitiveWord buildUpdateSensitiveWordResponse(SensitiveWords updatedWord, String oldWord) {
        return UpdateSensitiveWord.builder()
                .id(updatedWord.getId())
                .oldWord(oldWord)
                .newWord(updatedWord.getWord())
                .build();
    }
}
