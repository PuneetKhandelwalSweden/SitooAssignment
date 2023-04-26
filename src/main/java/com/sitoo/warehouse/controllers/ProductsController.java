package com.sitoo.warehouse.controllers;

import com.sitoo.warehouse.models.Products;
import com.sitoo.warehouse.models.exceptions.BarcodeNotFoundException;
import com.sitoo.warehouse.models.exceptions.SkuNotFoundException;
import com.sitoo.warehouse.models.json.ErrorResponse;
import com.sitoo.warehouse.models.json.GetAllProductsResponse;
import com.sitoo.warehouse.models.json.Items;
import com.sitoo.warehouse.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductsController {

    @Autowired
    ProductsRepository productsRepository;

    @PostMapping("/api/products")
    public ResponseEntity insertProduct(@RequestBody Products products) {
        Products savedProduct = null;
        try {
            savedProduct = productsRepository.save(products);
        } catch (DataIntegrityViolationException e) {
            if (productsRepository.findBySku(products.getSku())!=null) {
                return new ResponseEntity(new ErrorResponse(10002, "An exception is caused because of saving non unique value of SKU : " + products.getSku() + "."), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else {
                return new ResponseEntity(new ErrorResponse(10003, "An exception is caused because of saving non unique value of barcode : "+products.getBarcodes() +" ."), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity(new ErrorResponse(10001, "An exception is caused while saving the request."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedProduct.getProductId(), HttpStatus.CREATED);
    }

    @GetMapping("/api/products")
    public ResponseEntity getProducts(@RequestParam(name = "start", defaultValue = "0") int start,
                                      @RequestParam(name = "num", defaultValue = "10") int num,
                                      @RequestParam(name = "sku", defaultValue = "", required = false) String sku,
                                      @RequestParam(name = "barcode", defaultValue = "", required = false) String barcode) {
        GetAllProductsResponse getAllProductsResponse = new GetAllProductsResponse();
        try {
            long count = productsRepository.count();
            getAllProductsResponse.setTotalCount(count);
            List<Products> topN = new ArrayList<>();
            topN.addAll(productsRepository.findTopN(start, num));
            if (!sku.equalsIgnoreCase("")) {
                try {
                    Products productBySku=productsRepository.findBySku(sku);
                    if(productBySku==null)
                    {
                        throw new SkuNotFoundException();
                    }
                    topN.add(productBySku);
                } catch (SkuNotFoundException skuNotFoundException) {
                   skuNotFoundException.printStackTrace();
                    return new ResponseEntity(new ErrorResponse(10004, "The SKU : " + sku + " is not found."), HttpStatus.BAD_REQUEST);
                }
            }
            if (!barcode.equalsIgnoreCase("")) {
                try {
                    Products productByBarcode=productsRepository.findByBarcode(barcode);
                    if(productByBarcode==null)
                    {
                        throw new BarcodeNotFoundException();
                    }
                    topN.add(productByBarcode);
                }
                catch (BarcodeNotFoundException barcodeNotFoundException) {
                    return new ResponseEntity(new ErrorResponse(10005, "The Barcode : " + barcode + " is not found."), HttpStatus.BAD_REQUEST);
                }
            }
            List<Items> listOfItems = topN.stream().map(products -> new Items(products.getProductId(), products.getTitle())).collect(Collectors.toList());
            getAllProductsResponse.setItems(listOfItems);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(10001, "An exception is caused while saving the request."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(getAllProductsResponse,HttpStatus.OK);
    }

}
