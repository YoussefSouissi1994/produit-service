package tn.ipsas.produitservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.ipsas.coremodels.exceptions.EntityNotFoundException;
import tn.ipsas.coremodels.models.produit.Category;
import tn.ipsas.coremodels.models.produit.Product;
import tn.ipsas.produitservice.data.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }
    public Page<Category> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public Category getById(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }
    public Category save(Category category) {
        Category categorySaved = repository.save(category);
        return categorySaved;
    }
    public void delete(String id) {
        Category category = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        repository.deleteById(id);
    }
    public boolean exists(String id) {
        return repository.existsById(id);
    }
}
