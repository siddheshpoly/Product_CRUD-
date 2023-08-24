package com.example.ProductController;

import com.example.ProductException.ProductNotFoundException;
import com.example.ProductModel.Product;
import com.example.ProductRepository.ProductRepository;
import com.example.Utility.GlobalResources;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productapp")
public class ProductController {
    private Logger logger = GlobalResources.getLogger(ProductController.class);
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts()  {
        logger.info("Get All products");
        List<Product> result = productRepository.findAll();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long ProductId) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(ProductId);
        if(product.isPresent())
        {
            Product result = product.get();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product)
    {
        Product result = productRepository.save(product);
        if(result != null)
        {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{ProductId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long ProductId, @RequestBody Product product)
    {
        Product updatedproduct = productRepository.findById(ProductId).orElseThrow(()-> new ProductNotFoundException(ProductId, "Not found"));
        updatedproduct.setProductName(product.getProductName());
        updatedproduct.setProductDescription(product.getProductDescription());
        updatedproduct.setProductPrice(product.getProductPrice());
        Product result = productRepository.save(updatedproduct);
        if(result != null)
        {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable("id") Long ProductId)
    {
      Product product = productRepository.findById(ProductId).orElseThrow(()-> new ProductNotFoundException(ProductId, "not found"));
       productRepository.deleteById(ProductId);

    }


}
