package com.example.wholeProject.service.serviceImp;


import com.example.wholeProject.dto.ProductDto;
import com.example.wholeProject.dto.StockDto;
import com.example.wholeProject.entities.Product;
import com.example.wholeProject.entities.Stock;
import com.example.wholeProject.exception.ResourceNotFoundException;
import com.example.wholeProject.repositries.StockRepository;
import com.example.wholeProject.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class StockServiceImp implements StockService {
    @Autowired
    StockRepository stockRepository;

    public StockServiceImp (StockRepository stockRepository){this.stockRepository = stockRepository;}

    @Override
    public List<StockDto> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream().map(stock -> mapToDto(stock)).collect(Collectors.toList());

    }

    @Override
    public StockDto getStockById(Integer id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(StockServiceImp.class.getName(), "id", id));
        return mapToDto(stock);
    }

    @Override
    public StockDto addStock(StockDto stockDto) {
        Stock stock = mapToEntity(stockDto);
        Stock newStock = stockRepository.save(stock);
        return mapToDto(newStock);
    }

    @Override
    public StockDto updateStock(StockDto stockDto, Integer id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(StockServiceImp.class.getName(), "id", id));

        stock.setId(stock.getId());
        stock.setProduct(mapToProductEntity(stockDto.getProductDto()));
        stock.setQuantity(stockDto.getQuantity());
        stock.setUpdateAt(stock.getUpdateAt());

        Stock updatedStock = stockRepository.save(stock);
        StockDto updatedStockDto = mapToDto(updatedStock);
        return updatedStockDto;
    }

    @Override
    public void deleteStock(Integer id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(StockServiceImp.class.getName(), "id", id));
        stockRepository.delete(stock);
    }

    @Override
    public int getQuantityProductByName(String name) {
        int quantity = stockRepository.findQuantityOfProductByName(name);
        return quantity;
    }

    @Override
    public Date getUpdatedDateOfStockByProductName(String name) {
        Date date = stockRepository.findUpdatedDateOfProductByName(name);
        return date;
    }

    private Stock mapToEntity(StockDto stockDto) {
        Stock stock = new Stock();
        //stock.setId(stock.getId());
        stock.setProduct(mapToProductEntity(stockDto.getProductDto()));
        stock.setQuantity(stockDto.getQuantity());
        stock.setUpdateAt(stock.getUpdateAt());
        return stock;
    }

    private StockDto mapToDto(Stock stock) {

        StockDto stockDto = new StockDto();

        stockDto.setId(stock.getId());
        stockDto.setProductDto(mapTopProductDto(stock.getProduct()));
        stockDto.setQuantity(stock.getQuantity());
        stockDto.setUpdatedAt(stock.getUpdateAt());

        return stockDto;

    }
    private ProductDto mapTopProductDto(Product product){

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
    private Product mapToProductEntity(ProductDto productDto) {
        Product product = new Product();

        //product.setId(productDto.getId());
        product.setName(product.getName());
        product.setPrice(productDto.getPrice());
        product.setReference(productDto.getReference());
        product.setSlug(productDto.getSlug());
        product.setStockAble(productDto.isStockAble());
        product.setVat(productDto.getVat());

        return product;
    }
}
