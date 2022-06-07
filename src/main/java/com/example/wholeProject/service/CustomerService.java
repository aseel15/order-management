package com.example.wholeProject.service;

import com.example.wholeProject.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerService {
     List<CustomerDto> getAllCustomers();

     CustomerDto getCustomerById(int id);

     CustomerDto addCustomer(CustomerDto customerDto);

     CustomerDto updateCustomer(CustomerDto customerDto, int id);

     void deleteCustomer(int id);

     List<CustomerDto> getCustomerByFirstNameOrderedByLastName(String firstName);

     List<CustomerDto> getCustomersOrderedOverPrice(double price);

}
