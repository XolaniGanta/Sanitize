package com.snapplify.microservice.controller;

import com.snapplify.microservice.pojo.request.SanitizeWordsRequest;
import com.snapplify.microservice.pojo.request.SensitiveWordsRequest;
import com.snapplify.microservice.pojo.request.UpdateSensitiveWords;
import com.snapplify.microservice.pojo.response.GetSensitiveWordsResponse;
import com.snapplify.microservice.pojo.response.SanitizeWordsResponse;
import com.snapplify.microservice.pojo.response.SensitiveWordsResponse;
import com.snapplify.microservice.pojo.response.UpdateSensitiveWord;
import com.snapplify.microservice.service.SensitiveWordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensitive-words")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342/")
public class SensitiveWordsController {
    private final SensitiveWordService sensitiveWordService;

    @Operation(summary = "Adds one or more sensitive words to the database",
            responses = @ApiResponse(content = @Content(schema = @Schema(implementation = SensitiveWordsResponse.class))))
    @PostMapping("/add")
    public ResponseEntity<SensitiveWordsResponse> addSensitiveWords(@RequestBody SensitiveWordsRequest sensitiveWordsRequest) {
        return ResponseEntity.ok().body(sensitiveWordService.addSensitiveWords(sensitiveWordsRequest));
    }

    @Operation(summary = "Retrieves all sensitive words ",
            responses = @ApiResponse(content = @Content(schema = @Schema(implementation = GetSensitiveWordsResponse.class))))
    @GetMapping("/all")
    public ResponseEntity<GetSensitiveWordsResponse> getAllSensitiveWords() {
        return ResponseEntity.ok().body(sensitiveWordService.getAllSensitiveWords());
    }

    @Operation(summary = "Sanitizes input by replacing sensitive words with asterisks",
            responses = @ApiResponse(content = @Content(schema = @Schema(implementation = SanitizeWordsResponse.class))))
    @PostMapping("/sanitize")
    public ResponseEntity<SanitizeWordsResponse> getSensitiveWords(@RequestBody SanitizeWordsRequest sanitizeWordsRequest) {
        return ResponseEntity.ok().body(sensitiveWordService.SanitizeWords(sanitizeWordsRequest));
    }

    @Operation(summary = "Deletes a specific sensitive word by its ID",
            responses = @ApiResponse())
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSensitiveWord(@PathVariable long id) {
        sensitiveWordService.deleteSensitiveWordById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Updates existing sensitive words in the database",
            responses = @ApiResponse(content = @Content(schema = @Schema(implementation = UpdateSensitiveWord.class))))
    @PutMapping("/update")
    public ResponseEntity<UpdateSensitiveWord> updateSensitiveWord(@RequestBody UpdateSensitiveWords updateSensitiveWords) {
        return ResponseEntity.ok().body(sensitiveWordService.updateSensitiveWords(updateSensitiveWords));
    }


}


