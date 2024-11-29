package com.snapplify.microservice.repository;

import com.snapplify.microservice.entity.SensitiveWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensitiveWordsRepo extends JpaRepository<SensitiveWords, Long> {

    @Query(value = "SELECT word FROM Sensitive_Words " +
            "WHERE LOWER(:inputString) LIKE CONCAT('%', LOWER(word), '%')", nativeQuery = true)
    List<String> findMatchingWords(@Param("inputString") String inputString);

    @Query(value = "SELECT word FROM Sensitive_Words", nativeQuery = true)
    List<String> getAllSensitiveWords();

    Optional<SensitiveWords> findById(Long id);

    List<SensitiveWords> findAllByWordIn(List<String> words);

}
