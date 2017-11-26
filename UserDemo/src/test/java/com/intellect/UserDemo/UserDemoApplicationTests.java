package com.intellect.UserDemo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.intellect.model.User;
import com.intellect.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDemoApplicationTests {

	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void saveUser_validData() {
		User newUserRequst =  generateNewUser();
	    User savedUser = userRepo.saveUser(generateNewUser());
		assertThat(savedUser.getEmail().equals(newUserRequst.getEmail()));
		assertThat(userRepo.getUser(savedUser.getId()));
	}

	@Test
	public void updateUser() {
		User newUserRequst =  generateNewUser();
	    User savedUser = userRepo.saveUser(generateNewUser());
		assertThat(savedUser.getEmail().equals(newUserRequst.getEmail()));
		assertThat(userRepo.getUser(savedUser.getId()));
		//do update
		User updatedUser = userRepo.updateUser(518003, "02-Aug-1992", savedUser);
		assertThat(updatedUser.getPinCode() == 518003);
		assertThat(updatedUser.getBirthDate().equals("02-Aug-1992"));
		assertThat(updatedUser.getPinCode() != savedUser.getPinCode());
	}
	
	public User generateNewUser() {
		User user = new User();
		user.setBirthDate("02-Aug-1993");
		// unique
		String email = "pavan"+System.currentTimeMillis()+"@gmail.com"; 
		user.setEmail(email);
		user.setfName("Pavan");
		user.setlName("Kumar");
		user.setPinCode(560100);
		return user;
	}
}
