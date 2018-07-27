package com.notes.NotesApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notes.NotesApplication.dao.repository.NotesRepository;
import com.notes.NotesApplication.dao.repository.UserInfoRepository;
import com.notes.NotesApplication.entity.Notes;
import com.notes.NotesApplication.entity.UserInfo;

@Service
public class UserNotesService {
	
	private final static String CLASS_NAME=UserNotesService.class.getName();
	private final static Logger logger=Logger.getLogger(CLASS_NAME);


	@Autowired
	private UserInfoRepository userInfoRepository;
	@Autowired
	private NotesRepository notesRepository;
	
	public List<UserInfo> fetchAllUserInformation() {
		final String methodName="fetchAllUserInformation";
		logger.entering(CLASS_NAME, methodName);
		final List<UserInfo> userList= new ArrayList<>();
		
		final Iterable<UserInfo> userInfoItr= this.userInfoRepository.findAll();
		userInfoItr.forEach(userList::add);
		
		return userList;
	}
	public void saveNotes(final Notes note) {
		this.notesRepository.save(note);
		logger.info(" Data saved successfully");
		
	}
	public UserInfo fetchAllUserInformation(final String userId) {
		final Optional<UserInfo> userOptional = this.userInfoRepository.findById(userId);
		return userOptional.get();
	}
	
	public void updateNote(final Notes notes) {
		this.notesRepository.save(notes);
		logger.info(" Notes updated successfully ");
		
	}
	public void deleteNotesInformation(final String noteId) {
		this.notesRepository.deleteById(noteId);
		logger.info(" Record deleted successfully ");
	}
	
	
}
