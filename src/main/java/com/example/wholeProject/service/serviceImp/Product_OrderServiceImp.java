package com.example.wholeProject.service.serviceImp;


import com.example.wholeProject.dto.CustomerDto;
import com.example.wholeProject.dto.OrderDto;
import com.example.wholeProject.dto.ProductDto;
import com.example.wholeProject.dto.Product_OrderDto;
import com.example.wholeProject.entities.Customer;
import com.example.wholeProject.entities.Order;
import com.example.wholeProject.entities.Product;
import com.example.wholeProject.entities.Product_Order;
import com.example.wholeProject.repositries.Product_OrderRepository;
import com.example.wholeProject.service.Product_OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Product_OrderServiceImp implements Product_OrderService {
    @Autowired
    Product_OrderRepository product_orderRepository;

    public Product_OrderServiceImp (Product_OrderRepository product_orderRepository){this.product_orderRepository = product_orderRepository;}

    /*@Override
    public List<Product_OrderDto> getAllProduct_Orders() {
        List<Product_Order> product_orders = product_orderRepository.findAll();
        return product_orders.stream().map(product_order -> mapToDto(product_order)).collect(Collectors.toList());

    }

    @Override
    public Product_OrderDto getProduct_OrdersById(Integer id) {
        Product_Order product_order = product_orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Product_OrderServiceImp.class.getName(), "id", id));
        return mapToDto(product_order);
    }


    @Override
    public Product_OrderDto addProduct_Order(Product_OrderDto product_orderDto) {
        Product_Order product_order = mapToEntity(product_orderDto);
        Product_Order newProduct_Order = product_orderRepository.save(product_order);
        return mapToDto(newProduct_Order);
    }

    @Override
    public Product_OrderDto updateProduct_Order(Product_OrderDto product_orderDto, Integer id) {
        Product_Order product_order = product_orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Product_OrderServiceImp.class.getName(), "id", id));
        product_order.setProduct(mapToProductEntity(product_orderDto.getProductDto()));
        product_order.setOrder(mapToOrderEntity(product_orderDto.getOrderDto()));
        product_order.setPrice(product_orderDto.getPrice());
        product_order.setVat(product_orderDto.getVat());
        product_order.setQuantity(product_orderDto.getQuantity());

        Product_Order updatedProduct_Order = product_orderRepository.save(product_order);
        Product_OrderDto updatedProduct_OrderDto = mapToDto(updatedProduct_Order);
        return updatedProduct_OrderDto;
    }

    @Override
    public void deleteProduct_Order(Integer id) {
        Product_Order product_order = product_orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Product_OrderServiceImp.class.getName(), "id", id));
        product_orderRepository.delete(product_order);
    }*/

    @Override
    public List<Product_OrderDto> getOrderProductByPrice(double price) {
        List<Product_Order> product_orders = product_orderRepository.findProductOrderByPrice(price);
        return product_orders.stream().map(product_order -> mapToDto(product_order)).collect(Collectors.toList());
    }

    @Override
    public List<Product_OrderDto> getOrderContainsProduct(String name) {
        List<Product_Order> product_orders = product_orderRepository.findOrderContainsProduct(name);
        return product_orders.stream().map(product_order -> mapToDto(product_order)).collect(Collectors.toList());
    }

    private Product_Order mapToEntity(Product_OrderDto product_orderDto) {
        Product_Order product_order = new Product_Order();
        product_order.setProduct(mapToProductEntity(product_orderDto.getProductDto()));
        product_order.setOrder(mapToOrderEntity(product_orderDto.getOrderDto()));
        product_order.setPrice(product_orderDto.getPrice());
        product_order.setVat(product_orderDto.getVat());
        product_order.setQuantity(product_orderDto.getQuantity());
        return product_order;
    }

    private Product_OrderDto mapToDto(Product_Order product_order) {
        //Product_OrderDto product_orderDto = new Product_OrderDto(product_order.getId(), product_order.getCustomer(), product_order.getProduct_OrderedAt());
        Product_OrderDto product_orderDto = new Product_OrderDto();
        product_orderDto.setProductDto(mapToProductDto(product_order.getProduct()));
        product_orderDto.setOrderDto(mapToOrderDto(product_order.getOrder()));

        product_orderDto.setPrice(product_order.getPrice());
        product_orderDto.setVat(product_order.getVat());
        product_orderDto.setQuantity(product_order.getQuantity());
        return product_orderDto;

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
    private Product mapToProductEntity(ProductDto productDto) {
        Product product = new Product();

        product.setId(productDto.getId());
        product.setName(product.getName());
        product.setPrice(productDto.getPrice());
        product.setReference(productDto.getReference());
        product.setSlug(productDto.getSlug());
        product.setStockAble(productDto.isStockAble());
        product.setVat(productDto.getVat());

        return product;
    }

    private ProductDto mapToProductDto(Product product) {
        //ProductDto productDto = new ProductDto(product.getId(), product.getCustomer(), product.getProductedAt());
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setVat(product.getVat());
        productDto.setStockAble(product.isStockAble());
        productDto.setSlug(product.getSlug());
        productDto.setReference(product.getReference());

        return productDto;

    }
    private Order mapToOrderEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomer(mapCustomerToDto(orderDto.getCustomerDto()));
        order.setOrderedAt(orderDto.getOrderedAt());
        return order;
    }

    private OrderDto mapToOrderDto(Order order) {
        //OrderDto orderDto = new OrderDto(order.getId(), order.getCustomer(), order.getOrderedAt());
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomerDto(mapToCustomerEntity(order.getCustomer()));
        orderDto.setOrderedAt(order.getOrderedAt());
        return orderDto;

    }

}
