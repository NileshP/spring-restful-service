package com.rasnil.restful.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rasnil.restful.model.User;
import com.rasnil.restful.service.UserService;

/**
 * 
 * @author Nilesh Parshetti
 *
 */

@RestController
@RequestMapping("/employee")
public class UserController {

	final static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> addEmployee(@RequestBody User employee) {
		userService.save(employee);
		logger.debug("Added:: " + employee);
		return new ResponseEntity<User>(employee, HttpStatus.CREATED);
	}


	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateEmployee(@RequestBody User employee) {
		User existingEmp = userService.getById(employee.getId());
		if (existingEmp == null) {
			logger.debug("Employee with id " + employee.getId() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			userService.save(employee);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getEmployee(@PathVariable("id") Long id) {
		User employee = userService.getById(id);
		if (employee == null) {
			logger.debug("Employee with id " + id + " does not exists");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Employee:: " + employee);
		return new ResponseEntity<User>(employee, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllEmployees() {
		List<User> employees = userService.getAll();
		if (employees.isEmpty()) {
			logger.debug("Employees does not exists");
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + employees.size() + " Employees");
		logger.debug(Arrays.toString(employees.toArray()));
		return new ResponseEntity<List<User>>(employees, HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
		User employee = userService.getById(id);
		if (employee == null) {
			logger.debug("Employee with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			userService.delete(id);
			logger.debug("Employee with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

}