package com.example.wholeProject.service;

import com.example.wholeProject.dto.StockDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public interface StockService {
    List<StockDto> getAllStocks();

    StockDto getStockById(Integer id);

    StockDto addStock(StockDto stockDto);

    StockDto updateStock(StockDto stockDto, Integer id);

    void deleteStock(Integer id);

    int getQuantityProductByName(String name);

    Date getUpdatedDateOfStockByProductName(String name);
}
