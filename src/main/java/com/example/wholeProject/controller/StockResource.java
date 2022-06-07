package com.example.wholeProject.controller;

import com.example.wholeProject.dto.StockDto;
import com.example.wholeProject.exception.BadRequestException;
import com.example.wholeProject.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Api(value = "The operations on the stock controller")
//to indicate that this class is controller
@RestController
@RequestMapping("/api/stocks")
public class StockResource {

    //to inject the service constructor
    @Autowired
    StockService stockService;

    public StockResource(StockService stockService){
        this.stockService = stockService;
    }

    @ApiOperation(value = "Get all the stocks REST API")
    //to indicate that this method is Get
    @GetMapping
    public ResponseEntity<List<StockDto>> getAllStocks(){ //get all stocks
        return ResponseEntity.ok().body(stockService.getAllStocks()); //call the method of from stock service
    }

    @ApiOperation(value = "Get the stock by id REST API")
    @GetMapping("id/{id}") //get the stock by id by passing it in the url
    // @PathVariable indicates that method param should be bound to url template var
    public ResponseEntity<StockDto> getStockById(@PathVariable(name = "id") int id){
        return ResponseEntity.ok().body(stockService.getStockById(id));
    }


   /* //get the stock by passing the id using request parameter
    @GetMapping("/requestParam")
    public ResponseEntity<StockDto> getStockById2(@RequestParam(name = "id") int id){
        return ResponseEntity.ok().body(stockService.getStockById(id));
    }*/

    @ApiOperation(value = "Create a new stock REST API")
    //use the post method
    @PostMapping
    //@Valid used to verify that the data field is not null
    public ResponseEntity<StockDto> addStock(@Valid @RequestBody StockDto stockDto){
        if (stockDto.getId() != null) {
            //log.error("Cannot have an ID {}", stockDto);
            throw new BadRequestException(StockResource.class.getSimpleName(),
                    "id");
        }
        return new ResponseEntity<>(stockService.addStock(stockDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update exist stock by id REST API")
    //to indicate that this method to update the data by passing the id using url
    @PutMapping("/{id}")
    public ResponseEntity<StockDto> updateStock(@Valid @RequestBody StockDto stockDto, @PathVariable(name = "id") int id){
        return new ResponseEntity<>(stockService.updateStock(stockDto, id), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an exist stock by id REST API")
    //to indicate that this method to delete the object by passing its id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable(name = "id") int id){
        stockService.deleteStock(id);
        return new ResponseEntity<>("successfully deleted!", HttpStatus.OK);
    }

    @ApiOperation(value = "Get the quantity in the stock of the product by its name REST API")
    @GetMapping("name/{name}")
    public ResponseEntity<Integer> getQuantityOfProductByName(@PathVariable(name = "name") String name){
        return ResponseEntity.ok().body(stockService.getQuantityProductByName(name));
    }
    @ApiOperation(value = "Get last updated date of the product in the stock by its name REST API")
    @GetMapping("checkDate/{name}")
    public ResponseEntity<Date> getUpdatedDateOfProductByName(@PathVariable(name = "name") String name){
        return ResponseEntity.ok().body(stockService.getUpdatedDateOfStockByProductName(name));
    }
}
