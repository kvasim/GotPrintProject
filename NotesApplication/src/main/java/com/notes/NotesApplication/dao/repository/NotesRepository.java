package com.notes.NotesApplication.dao.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.notes.NotesApplication.entity.Notes;

@Repository
@Transactional
public interface NotesRepository extends CrudRepository<Notes, String> {

}
