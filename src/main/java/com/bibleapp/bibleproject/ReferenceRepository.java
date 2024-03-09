package com.bibleapp.bibleproject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bibleapp.bibleproject.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface ReferenceRepository extends CrudRepository<Reference, Integer> {

    Optional<Reference> findByBookAndChapterAndVerse(String book, String chapter, String verse);

}