/**
 * 
 */
package com.notes.NotesApplication.service;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.notes.NotesApplication.dao.repository.UserInfoRepository;
import com.notes.NotesApplication.entity.UserInfo;
import com.notes.NotesApplication.exception.ApiException;

/**
 * @author KVASIM
 *
 */
@Component
public class UserValidation {

	private final static String CLASS_NAME=UserValidation.class.getName();
	private final static Logger logger=Logger.getLogger(CLASS_NAME);

	@Autowired
	private UserInfoRepository userInfoRepository;
	@Value(value = "${user.access.msg}")
	private String userMessage;
	

	public void validateUser(final String userId) throws ApiException {
		final Optional<UserInfo> userOptional = this.userInfoRepository.findById(userId);
		System.out.println(userOptional.isPresent());
		if(!userOptional.isPresent()) {
			throw new ApiException(HttpStatus.UNAUTHORIZED,this.userMessage,"UnAuthorized user");
		}
		logger.info(" User validated successfully ");
	}
}
