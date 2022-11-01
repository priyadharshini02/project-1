package com.priya.project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import javax.validation.Valid;

@Controller
public class controller {
	
	UserDao userdao;
	public controller(UserDao userdao) {
		super();
		this.userdao=userdao;
	}
	
	 @RequestMapping(value = "/register") 
	 public String displayLogin(Model model) { 
	     model.addAttribute("userForm", new User()); 
	     return "registration"; 
	 }
	 
	 @RequestMapping(value="/submitlogin")
	 public ModelAndView submitlogin(@RequestParam(name="email")String email, @RequestParam(name="password")String password) {
		 ModelAndView mv=new ModelAndView();
		 System.out.println(email);
		 Optional<User>user=userdao.findById(email);
		 if(user.isPresent()) {
			 User u1=user.get();
			 String dbemail=u1.getEmail(); 
			 String dbpassword=u1.getPassword();
			 
			 if(dbemail.equals(email)&& dbpassword.equals(password))
			 { 
				 
				 mv.addObject(u1);
				 mv.setViewName("dispaly");
			 
			 }
			 else {
				 mv.addObject("error","invalid user name or password");
				 System.out.println("invalid user name or password");
				 mv.setViewName("login");
			 }
		 }
		 else {
			 System.out.println("user does not exist");
			 mv.addObject("error","user does not exist please register");
			 mv.setViewName("login");
		 }
		 return mv;
	 }
	 

	 
	 @RequestMapping(value="/login")
	 public String login() {
		 
		 return "login";
	 }
	 
	@RequestMapping(value="/submitForm", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView register(@Valid @ModelAttribute("userForm") User userForm, BindingResult br) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("user",userForm);
		System.out.println(userForm.getFirstName());
		System.out.println("in controller");
		System.out.println(br);
		
		if(userForm.getPassword()!=null && userForm.getConfPassword()!=null) {
			if(!userForm.getPassword().equals(userForm.getConfPassword())) {
				br.addError(new FieldError("userForm","confPassword","password did not match"));
			}
		}
		System.out.println(userdao.findById(userForm.getEmail()));
		 Optional<User>user=userdao.findById(userForm.getEmail());
		 if(user.isPresent()) {
			br.addError(new FieldError("userForm","email","email already exists, please use login"));
		}
		
		if(br.hasErrors()) {
			
			mv.setViewName("registration");
			System.out.println("form has error so redirecting");
		}
		else {
				userdao.save(userForm);
				mv.addObject("error","registration successful, please login");
				mv.setViewName("login");
		}
		return mv;
	}
	
}
