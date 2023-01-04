package tn.ipsas.produitservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import tn.ipsas.coremodels.models.produit.Product;
import tn.ipsas.produitservice.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }
    @GetMapping
    public Page<Product> page(Pageable pageable) {
        return service.getAll(pageable);
    }
    @GetMapping("{id}")
    public Product byId(@PathVariable("id") String id) {
        return service.getById(id);
    }
    @PutMapping
    public Product add(@RequestBody Product product) {
        product.setId(null);
        return service.save(product);
    }
    @PutMapping("{id}")
    public Product update(@PathVariable("id") String id, @RequestBody Product product) {
        if (!service.exists(id)) {
            throw new IllegalArgumentException();
        }
        product.setId(id);
        return service.save(product);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        if (!service.exists(id)) {
            throw new IllegalArgumentException();
        }
        service.delete(id);
    }

}
