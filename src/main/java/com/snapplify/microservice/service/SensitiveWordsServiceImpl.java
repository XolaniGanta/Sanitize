package com.snapplify.microservice.service;

import com.snapplify.microservice.entity.SensitiveWords;
import com.snapplify.microservice.exception.SanitizeException;
import com.snapplify.microservice.pojo.constants.ErrorCode;
import com.snapplify.microservice.pojo.request.SanitizeWordsRequest;
import com.snapplify.microservice.pojo.request.SensitiveWordsRequest;
import com.snapplify.microservice.pojo.request.UpdateSensitiveWords;
import com.snapplify.microservice.pojo.response.*;
import com.snapplify.microservice.repository.SensitiveWordsRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.snapplify.microservice.util.Helper.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class SensitiveWordsServiceImpl implements SensitiveWordService {

    private final SensitiveWordsRepo sensitiveWordRepository;

    @Override
    public SensitiveWordsResponse addSensitiveWords(SensitiveWordsRequest sensitiveWordsRequest) {
        log.debug("addSensitiveWords - method entered - sensitiveWordsRequest: {}", sensitiveWordsRequest);

        List<String> newWords = sensitiveWordsRequest.getWords();

        List<String> uniqueWords = new ArrayList<>(new HashSet<>(newWords));

        List<String> existingWords = sensitiveWordRepository.findAllByWordIn(uniqueWords)
                .stream()
                .map(SensitiveWords::getWord)
                .toList();

        List<String> wordsToAdd = newWords.stream()
                .filter(word -> !existingWords.contains(word))
                .toList();

        if (wordsToAdd.isEmpty()) {
           log.error("All words already exist in the database.");
            throw new SanitizeException(ErrorCode.INVALID_INPUT);
        }

        List<SensitiveWords> sensitiveWords = sensitiveWordsRequest.getWords()
                .stream()
                .map(word -> new SensitiveWords(null, word))
                .toList();

        sensitiveWordRepository.saveAll(sensitiveWords);

        SensitiveWordsResponse response = buildSensitiveWordResponse(sensitiveWordsRequest);

        log.debug("addSensitiveWords - completed - response: {}", response);

        return response;
    }

    @Override
    public GetSensitiveWordsResponse getAllSensitiveWords() {
        log.debug("getAllSensitiveWords - method entered");

        List<String> sensitiveWords;
        try {
            sensitiveWords = sensitiveWordRepository.getAllSensitiveWords();
            log.info("SensitiveWords - retrieved : {}", sensitiveWords);
        } catch (Exception e) {
            log.error("SensitiveWords - error occurred while retrieving sensitive words", e);
            throw new SanitizeException(ErrorCode.DATABASE_ERROR);
        }

        GetSensitiveWordsResponse response = buildGetSensituveWordsResponse(sensitiveWords);

        log.debug("getAllSensitiveWords - completed - response: {}", response);

        return response;
    }

    @Override
    public SanitizeWordsResponse SanitizeWords(SanitizeWordsRequest sanitizeWordsRequest) throws SanitizeException {
        log.debug("getSensitiveWords - method entered - words: {}", sanitizeWordsRequest);

        String inputString = sanitizeWordsRequest.getInputString();

        if (inputString.isEmpty()) {
            log.error("SanitizeWords - input string is empty");
            throw new SanitizeException(ErrorCode.EMPTY_INPUT);
        }

        List<String> sensitiveWords;
        try {
            sensitiveWords = sensitiveWordRepository.findMatchingWords(inputString);
            log.info("SanitizeWords - retrieved sensitive words: {}", sensitiveWords);
        } catch (Exception e) {
            log.error("SanitizeWords - error occurred while retrieving sensitive words", e);
            throw new SanitizeException(ErrorCode.DATABASE_ERROR);
        }

        List<String> matchedWords = sensitiveWords.stream()
                .filter(word -> inputString.toLowerCase().contains(word.toLowerCase()))
                .toList();

        String sanitizedString = inputString;
        for (String word : matchedWords) {
            sanitizedString = sanitizedString.replaceAll("(?i)" + word, "*".repeat(word.length()));
        }

        SanitizeWordsResponse response = buildSanitizeWordsResponse(sanitizedString, matchedWords);

        log.debug("getSensitiveWords - completed - response: {}", response);

        return response;
    }


    @Override
    public UpdateSensitiveWord updateSensitiveWords(UpdateSensitiveWords updateSensitiveWordsRequest) {

        log.debug("updateSensitiveWord - method entered - updateRequest: {}", updateSensitiveWordsRequest);

        SensitiveWords sensitiveWord;
        sensitiveWord = sensitiveWordRepository.findById(Long.valueOf(updateSensitiveWordsRequest.getId()))
                .orElseThrow(() -> new EntityNotFoundException("Sensitive word not found with ID: " + updateSensitiveWordsRequest.getId()));

        String oldWord = sensitiveWord.getWord();

        sensitiveWord.setWord(updateSensitiveWordsRequest.getNewWord());
        sensitiveWordRepository.save(sensitiveWord);

        UpdateSensitiveWord response = buildUpdateSensitiveWordResponse(sensitiveWord, oldWord);

        log.debug("updateSensitiveWord - completed - response: {}", response);

        return response;
    }

    @Override
    public void deleteSensitiveWordById(long id) {
        log.debug("deleteSensitiveWordById - method entered - id: {}", id);

        if (!sensitiveWordRepository.existsById(id)) {
            log.error("Sensitive word - error occurred while deleting sensitive words");
            throw new SanitizeException(ErrorCode.SENSITIVE_WORD_NOT_FOUND);
        }
        sensitiveWordRepository.deleteById(id);

        log.debug("deleteSensitiveWordById - completed - id: {}", id);
    }

}

