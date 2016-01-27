/**
 * 
 */
package com.ceb.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ceb.service.ICebService;
import com.ceb.vo.CreateProject;

/**
 * @author Sahil Kaushik
 *
 */
@Controller
public class HomeController {

	@Autowired
	ICebService objectCebService;

	public void setObjectCebService(ICebService objectCebService) {
		this.objectCebService = objectCebService;
	}

	@RequestMapping("/welcome")
	public ModelAndView goToHomePage() {
		ModelAndView mv = new ModelAndView("basicsetting");

		return mv;
	}

	@RequestMapping(value = "/projectCreated", method = RequestMethod.POST)
	public ModelAndView registerUser(
			@RequestParam("projectName") String projectName,
			@RequestParam("date") String date,
			@RequestParam("selectionApplicationForm") String applicationForm,
			@RequestParam("selectionProjectTag") String projectTag,
			@RequestParam("projectDescription") String projectDescription)
			throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MMMMMM/yyyy");
		Date dateObject = dateFormat.parse(date);
		dateFormat.format(dateObject);
		ModelAndView mv;
		CreateProject objectCreateProject = new CreateProject();
		objectCreateProject.setProjectName(projectName);
		objectCreateProject.setDeadlineDate(dateObject);
		objectCreateProject.setApplicationForm(applicationForm);
		objectCreateProject.setProjectTag(projectTag);
		objectCreateProject.setProjectDescription(projectDescription);

		boolean status = objectCebService.addUserDetail(objectCreateProject);

		if (status) {
			mv = new ModelAndView("projectcreated");
			return mv;
		} else {
			mv = new ModelAndView("basicsetting");
			mv.addObject("messageForProjectName",
					"Project Name is Mandatory field*");
			mv.addObject("messageForDeadlineDate", "Invalid Date*");
			return mv;
		}
	}

}
