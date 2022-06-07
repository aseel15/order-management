package com.example.wholeProject.service.serviceImp;


import com.example.wholeProject.dto.CustomerDto;
import com.example.wholeProject.entities.Customer;
import com.example.wholeProject.exception.ResourceNotFoundException;
import com.example.wholeProject.repositries.CustomerRepository;
import com.example.wholeProject.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomerServiceImp implements CustomerService {
    CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> mapToDto(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CustomerServiceImp.class.getName(), "id", id));
        return mapToDto(customer);
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer = mapToEntity(customerDto);
        Customer newCustomer = customerRepository.save(customer);
        CustomerDto customerResponse = mapToDto(newCustomer);
        return customerResponse;
        //return mapToDto(newCustomer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CustomerServiceImp.class.getName(), "id", id));
        customer.setId(customer.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBornAt(customerDto.getBornAt());

        Customer updatedCustomer = customerRepository.save(customer);
        CustomerDto updatedCustomerDto = mapToDto(updatedCustomer);
        return updatedCustomerDto;
    }

    @Override
    public void deleteCustomer(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CustomerServiceImp.class.getName(), "id", id));
        customerRepository.delete(customer);
    }

    @Override
    public List<CustomerDto> getCustomerByFirstNameOrderedByLastName(String firstName) {
        List<Customer> customers = customerRepository.findByFirstNameOrderByLastname(firstName);
        return customers.stream().map(customer -> mapToDto(customer)).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getCustomersOrderedOverPrice(double price) {
        List<Customer> customers = customerRepository.findCustomersOrderedOverPrice(price);
        return customers.stream().map(customer -> mapToDto(customer)).collect(Collectors.toList());
    }

    private Customer mapToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        //customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBornAt(customerDto.getBornAt());

        return customer;
    }

    private CustomerDto mapToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getBornAt());
        return customerDto;

    }
}
