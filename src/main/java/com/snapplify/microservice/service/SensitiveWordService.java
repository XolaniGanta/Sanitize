package com.snapplify.microservice.service;

import com.snapplify.microservice.pojo.request.SanitizeWordsRequest;
import com.snapplify.microservice.pojo.request.SensitiveWordsRequest;
import com.snapplify.microservice.pojo.request.UpdateSensitiveWords;
import com.snapplify.microservice.pojo.request.UpdateSensitiveWordsRequest;
import com.snapplify.microservice.pojo.response.*;

public interface SensitiveWordService {
    SensitiveWordsResponse addSensitiveWords(SensitiveWordsRequest sensitiveWordsRequest);
    GetSensitiveWordsResponse getAllSensitiveWords();
    SanitizeWordsResponse SanitizeWords(SanitizeWordsRequest getSensitiveWordRequest);
    UpdateSensitiveWord updateSensitiveWords(UpdateSensitiveWords updateSensitiveWordsRequest);
    void deleteSensitiveWordById(long id);

}
