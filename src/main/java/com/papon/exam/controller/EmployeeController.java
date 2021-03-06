package com.papon.exam.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.papon.exam.dto.EmployeeDto;
import com.papon.exam.service.EmployeeService;

@RestController
@RequestMapping("employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping()
	public ResponseEntity<List<EmployeeDto>> getEmployees() {
		try {
			return ResponseEntity.ok(employeeService.getAll());
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployees(@PathVariable("id") final Long id) {
		try {
			return ResponseEntity.ok(employeeService.findById(id));
		} catch (EntityNotFoundException enx) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") final Long id) {
		try {
			employeeService.deleteById(id);
			return ResponseEntity.ok(null);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") final Long id,@RequestBody final EmployeeDto employeeVo) {
		try {
			employeeVo.setId(id);
			return ResponseEntity.ok(employeeService.updateEmployee(employeeVo));
		} catch (EntityNotFoundException enx) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping()
	public ResponseEntity<EmployeeDto> insertEmployee(@RequestBody final EmployeeDto employeeVo) {
		try {
			return ResponseEntity.ok(employeeService.insertEmployee(employeeVo));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
