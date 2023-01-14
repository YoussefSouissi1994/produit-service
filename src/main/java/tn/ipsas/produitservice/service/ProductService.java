package tn.ipsas.produitservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tn.ipsas.coremodels.exceptions.EntityNotFoundException;
import tn.ipsas.coremodels.models.client.Client;
import tn.ipsas.coremodels.models.produit.Product;
import tn.ipsas.produitservice.data.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProductService(
            ProductRepository repository,
            KafkaTemplate<String, Object> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Page<Product> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public List<Product> getAll() {
        return repository.findAll();
    }
    public Product getById(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }
    public Product save(Product product) {
        boolean add = product.getId() == null;
        Product productSaved = repository.save(product);
        if (add) {
            kafkaTemplate.send("product_add", productSaved);
        }
        return productSaved;
    }
    public void delete(String id) {
        Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        repository.deleteById(id);
        kafkaTemplate.send("product_remove", product);
    }
    public boolean exists(String id) {
        return repository.existsById(id);
    }


}
