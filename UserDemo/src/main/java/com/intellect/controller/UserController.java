package com.intellect.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intellect.model.GenericMessage;
import com.intellect.model.User;
import com.intellect.repository.UserRepository;
import com.intellect.utils.Utils;

@Controller
@RequestMapping("/api")
public class UserController extends BaseController{

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method=RequestMethod.POST,path="/user/create")
	private ResponseEntity<?> saveUser(@Valid @RequestBody(required=true) User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(new GenericMessage("Request validation failed.Failed to create user.",
					"",getValidationErrors(bindingResult)));
		}
		if(userRepository.isUserExists(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new GenericMessage("User already exists",
					userRepository.getUserByEmail(user.getEmail()).getId()));
		}else if (!Utils.validateDate(user.getBirthDate())) {
			return ResponseEntity.badRequest().body(new GenericMessage("Invalid birthdate.Birthdate should "
					+ "be in format dd-MMM-YYYY and must be past date", ""));
		}
		
		//HttpHeaders responseHeaders = new HttpHeaders();
		//responseHeaders.setLocation(UriComponentsBuilder.newInstance().path("/api/user/{id}").
			//	buildAndExpand(userRepository.saveUser(user).getId()).toUri());
		return ResponseEntity.status(HttpStatus.CREATED).body(new GenericMessage("User created successfully",
				userRepository.saveUser(user).getId()));
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/user/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<?> getUser(@PathVariable(name="id",required=true) String userId){
		if(StringUtils.isEmpty(userId)) {
			return ResponseEntity.badRequest().body(new GenericMessage("Invalid userId",userId));
		}
		User user = userRepository.getUser(userId);
		if(user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericMessage("User  doesn't exists",
					userId));
		}
		if(user != null && !user.isActive()) {
			return ResponseEntity.ok().body(new GenericMessage("User is deactivated",userId));
		}
		return ResponseEntity.ok(user);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method=RequestMethod.PUT,path="/user/")
	private ResponseEntity<?> updateUser(@Valid @RequestBody(required=true) User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(new GenericMessage("Request validation failed.Failed to update user.",
					user.getId(),getValidationErrors(bindingResult)));
		}
		User fetchUser = userRepository.getUser(user.getId());
		if(fetchUser == null) {
			return new ResponseEntity(new GenericMessage("User not found.",
					user.getId()),HttpStatus.NOT_FOUND);
		}
		if(!fetchUser.isActive()) {
			return  ResponseEntity.status(HttpStatus.CONFLICT).body(new GenericMessage("Unable to update.UserId deactivated.",
					fetchUser.getId()));
		}else if (!Utils.validateDate(user.getBirthDate())) {
			return ResponseEntity.badRequest().body(new GenericMessage("Invalid birthdate.Birthdate should "
					+ "be in format dd-MMM-YYYY and must be past date", ""));
		}
		User updatedUser = userRepository.updateUser(user.getPinCode(), user.getBirthDate(), fetchUser);
		return ResponseEntity.ok(new GenericMessage("User details successfully updated", updatedUser.getId()));
	}
	
	@RequestMapping(method=RequestMethod.DELETE,path="/user/{id}")
	private ResponseEntity<?> deleteUser(@PathVariable(name="id",required=true) String id){
		User fetchUser = userRepository.getUser(id);
		if(fetchUser == null) {
			return new ResponseEntity<>(new GenericMessage("User not found.",id),HttpStatus.NOT_FOUND);
		}
		userRepository.deleteUser(fetchUser);
		return ResponseEntity.ok(new GenericMessage("User is deactivated", id));
	}
}
