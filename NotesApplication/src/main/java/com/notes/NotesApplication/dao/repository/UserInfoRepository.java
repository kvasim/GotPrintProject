package com.notes.NotesApplication.dao.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.notes.NotesApplication.entity.UserInfo;

@Repository
@Transactional
public interface UserInfoRepository extends CrudRepository<UserInfo, String> {

}
