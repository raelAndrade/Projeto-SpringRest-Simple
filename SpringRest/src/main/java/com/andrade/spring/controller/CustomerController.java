package com.andrade.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.andrade.spring.dao.CustomerDAO;
import com.andrade.spring.model.Customer;

@RestController
public class CustomerController {

	@Autowired
	private CustomerDAO customerDAO;
	
	@GetMapping("/clientes")
	public List getCustomers() {
		return customerDAO.list();
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {

		Customer cliente = customerDAO.get(id);
		if (cliente == null) {
			return new ResponseEntity("Nenhum cliente encontrado para identificação " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(cliente, HttpStatus.OK);
	}
	
	@PostMapping(value = "/clientes")
	public ResponseEntity createCustomer(@RequestBody Customer cliente) {

		customerDAO.create(cliente);

		return new ResponseEntity(cliente, HttpStatus.OK);
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity deleteCustomer(@PathVariable Long id) {

		if (null == customerDAO.delete(id)) {
			return new ResponseEntity("Nenhum cliente encontrado para identificação " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer cliente) {

		cliente = customerDAO.update(id, cliente);

		if (null == cliente) {
			return new ResponseEntity("Nenhum cliente encontrado para identificação " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(cliente, HttpStatus.OK);
	}
	
}
