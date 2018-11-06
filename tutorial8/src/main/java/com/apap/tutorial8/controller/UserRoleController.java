package com.apap.tutorial8.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user) {
		userService.addUser(user);
		return "home";
	}
	
//	@RequestMapping(value = "/checkOldPass", method = RequestMethod.GET)
//	@ResponseBody
//	private boolean checkOldPassIsValid(@RequestParam(value = "oldPass") String oldPass, Principal principal) {
//		boolean oldPassIsValid = userService.checkPassIsValid(principal.getName(), oldPass);
//		return oldPassIsValid;
//	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	private String updatePassword(Principal principal, HttpServletRequest req, Model model) {
		String username = principal.getName();
		String oldPass = req.getParameter("oldPass");
		String newPass = req.getParameter("newPass");
		
		boolean oldPassIsValid = userService.checkPassIsValid(username, oldPass);
		
		if (oldPassIsValid) {
			userService.updatePassword(username, newPass);
			model.addAttribute("updateResponse", "Selamat password Anda telah berhasil diperbarui!");
		}
		else {
			model.addAttribute("updateResponse", "Password lama Anda salah. Silahkan mencoba kembali!");
		}
		return "password-updated";
	}
}
