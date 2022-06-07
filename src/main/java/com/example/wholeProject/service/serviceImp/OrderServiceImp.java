package com.example.wholeProject.service.serviceImp;

import com.example.wholeProject.dto.CustomerDto;
import com.example.wholeProject.dto.OrderDto;
import com.example.wholeProject.entities.Customer;
import com.example.wholeProject.entities.Order;
import com.example.wholeProject.exception.ResourceNotFoundException;
import com.example.wholeProject.repositries.OrderRepository;
import com.example.wholeProject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    public OrderServiceImp (OrderRepository orderRepository){this.orderRepository = orderRepository;}
    
    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> mapToDto(order)).collect(Collectors.toList());
        
    }

    @Override
    public OrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(OrderServiceImp.class.getName(), "id", id));
        return mapToDto(order);
    }

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        Order order = mapToEntity(orderDto);
        Order newOrder = orderRepository.save(order);
        return mapToDto(newOrder);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(OrderServiceImp.class.getName(), "id", id));
        order.setId(orderDto.getId());
        order.setOrderedAt(orderDto.getOrderedAt());

        Order updatedOrder = orderRepository.save(order);
        OrderDto updatedOrderDto = mapToDto(updatedOrder);
        return updatedOrderDto;
    }

    @Override
    public void deleteOrder(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(OrderServiceImp.class.getName(), "id", id));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Integer id) {
        List<Order> orders = orderRepository.findOrderedByCustomerId(id);
        return orders.stream().map(order -> mapToDto(order)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersToday() {
        List<Order> orders = orderRepository.findOrdersToday();
        return orders.stream().map(order -> mapToDto(order)).collect(Collectors.toList());
    }

    private Order mapToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(order.getId());
        order.setCustomer(mapCustomerToDto(orderDto.getCustomerDto()));
        order.setOrderedAt(orderDto.getOrderedAt());
        return order;
    }

    private OrderDto mapToDto(Order order) {
        //OrderDto orderDto = new OrderDto(order.getId(), order.getCustomer(), order.getOrderedAt());
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomerDto(mapToCustomerEntity(order.getCustomer()));
        orderDto.setOrderedAt(order.getOrderedAt());
        return orderDto;

    }
    private Customer mapCustomerToDto(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBornAt(customerDto.getBornAt());
        return customer;
    }
    private CustomerDto mapToCustomerEntity(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setBornAt(customer.getBornAt());
        return customerDto;
    }

}
