package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.nt.login.LoginDao;

public class LoginController {
	@Autowired
	LoginDao dao;

	public String login(String username, String password) throws Exception {
		int count = 0;
		String role = null;
		count = dao.login(username, password);
		if (count == 0) {
			return "errorPage";
		} else {
			role = dao.role(username, password);
			if (role.equals("admin")) {
				return "admin";       // admin dashbord admin.jsp
			} else if (role.equals("student")) {    
				return "student";     // student dashbord student.jsp
			} else {
				return "employee";    // employee dashbord employee.jsp
			}
		}
	}
}