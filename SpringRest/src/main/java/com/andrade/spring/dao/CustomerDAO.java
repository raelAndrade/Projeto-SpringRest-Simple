package com.andrade.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.andrade.spring.model.Customer;

@Component
public class CustomerDAO {

	private static List<Customer> clientes;
	{
		clientes = new ArrayList();
		clientes.add(new Customer(101, "João", "Benedito", "joaob@gmail.com", "123-456-7890"));
		clientes.add(new Customer(201, "José", "Maria", "josem@gmail.com", "321-654-0987"));
		clientes.add(new Customer(301, "Maria", "José", "mariaj@gmail.com", "987-654-3210"));
	}

	public List<Customer> list() {
		return clientes;
	}
	
	public Customer get(Long id) {

		for (Customer c : clientes) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	public Customer create(Customer cliente) {
		cliente.setId(System.currentTimeMillis());
		clientes.add(cliente);
		return cliente;
	}
	
	public Long delete(Long id) {

		for (Customer c : clientes) {
			if (c.getId().equals(id)) {
				clientes.remove(c);
				return id;
			}
		}

		return null;
	}

	public Customer update(Long id, Customer cliente) {

		for (Customer c : clientes) {
			if (c.getId().equals(id)) {
				cliente.setId(c.getId());
				clientes.remove(c);
				clientes.add(cliente);
				return cliente;
			}
		}
		return null;
	}

}