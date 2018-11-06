package com.apap.tutorial8.service;

import com.apap.tutorial8.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	void updatePassword(String username, String newPass);
	String encrypt(String password);
	UserRoleModel getByUsername(String username);
	Boolean checkPassIsValid(String username, String password);
}
