package com.intellect.repository;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.intellect.model.User;

@Service
public class UserRepository{

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private Map<String,User> userData = new HashMap<>();
	
	public User saveUser(User user) {
		user.setId(randomAlphaNumeric(ThreadLocalRandom.current().nextInt(9,10)));
		user.setActive(true);
		userData.put(user.getId(), user);
		return user;
	}

	public boolean isUserExists(String emailId) {
		return userData.values().stream().filter(aUser->aUser.isActive()).
				map(User::getEmail).collect(Collectors.toList()).contains(emailId);
	}
	public User updateUser(Integer pinCode,String birthDate,User user) {
		user.setPinCode(pinCode);
		user.setBirthDate(birthDate);
		return user;
	}
	public void deleteUser(User user) {
		user.setActive(false);
	}
	public User getUser(String userId) {
		return userData.get(userId);
	}
	public User getUserByEmail(String email)	{
		return userData.values().stream().
				filter(user -> (user.isActive() && user.getEmail().equals(email))).findFirst().get();
	}
	
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}
