/*
 * UserNotes is the main controller class
 */
package com.notes.NotesApplication.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.NotesApplication.entity.Notes;
import com.notes.NotesApplication.entity.UserInfo;
import com.notes.NotesApplication.exception.ApiException;
import com.notes.NotesApplication.service.UserNotesService;
import com.notes.NotesApplication.service.UserValidation;

@RestController
@RequestMapping("/api")
public class UserNotesController {
	
	private final static String CLASS_NAME=UserNotesController.class.getName();
	private final static Logger logger=Logger.getLogger(CLASS_NAME);

	@Autowired
	private UserNotesService userNoteService;
	@Autowired
	private UserValidation userValidation;

	@GetMapping(value = "/allUserInfo")
	public List<UserInfo> getAllUserInformation(final HttpServletRequest request) {
		final String methodName="getAllUserInformation";
		logger.entering(CLASS_NAME, methodName);
		return this.userNoteService.fetchAllUserInformation();
	}

	@GetMapping(value = "/userInfo")
	public UserInfo getUserInformation(final HttpServletRequest request) {
		final String methodName="getUserInformation";
		logger.entering(CLASS_NAME, methodName);
		final String userId= request.getHeader("USERID");
		return this.userNoteService.fetchAllUserInformation(userId);
	}
	
	@PostMapping(value = "/notes")
	public void createNotes(@RequestBody final Notes note) {
		final String methodName="createNotes";
		logger.entering(CLASS_NAME, methodName);
		this.userNoteService.saveNotes(note);
		logger.info("Note information saved successfully");
		
	}
	
	@PutMapping(value="/updateNotes")
	public void updateNotes(final HttpServletRequest request, @RequestBody final Notes notes) throws ApiException {
		final String methodName="updateNotes";
		logger.entering(CLASS_NAME, methodName);
		final String userId= request.getHeader("USERID");
		this.userValidation.validateUser(userId);
		this.userNoteService.updateNote(notes);
		
	}
	
	@DeleteMapping(value="/deleteNote/{noteId}")
	public void deleteNote(final HttpServletRequest request, @PathVariable final String noteId) throws ApiException {
		final String methodName = "deleteNote";
		logger.entering(CLASS_NAME, methodName);
		final String userId= request.getHeader("USERID");
		logger.info("Note Id-" +noteId);
		this.userValidation.validateUser(userId);
		
		this.userNoteService.deleteNotesInformation(noteId);
		logger.exiting(CLASS_NAME, methodName);
	}
}
