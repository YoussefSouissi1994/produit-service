package tn.ipsas.produitservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import tn.ipsas.coremodels.models.produit.Category;
import tn.ipsas.produitservice.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categorie")
public class CategoryController {
    private final CategoryService service;


    public CategoryController(CategoryService service) {
        this.service = service;
    }
    @GetMapping
    public Page<Category> page(Pageable pageable) {
        return service.getAll(pageable);
    }
    @GetMapping("all")
    public List<Category> all() {
        return service.getAll();
    }
    @GetMapping("{id}")
    public Category byId(@PathVariable("id") String id) {
        return service.getById(id);
    }
    @PutMapping
    public Category add(@RequestBody Category category) {
        category.setId(null);
        Category result =  service.save(category);

        return result;
    }
    @PutMapping("{id}")
    public Category update(@PathVariable("id") String id, @RequestBody Category category) {
        if (!service.exists(id)) {
            throw new IllegalArgumentException();
        }
        category.setId(id);
        return service.save(category);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        if (!service.exists(id)) {
            throw new IllegalArgumentException();
        }
        service.delete(id);
    }

}
