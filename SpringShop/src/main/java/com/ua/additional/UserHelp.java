package com.ua.additional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.ua.model.User;
import com.ua.service.UserService;

public class UserHelp {
	@Autowired
	private UserService userService;


	public boolean checkEmailValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);

		return pat.matcher(email).matches();

	}
	
}
