package tn.ipsas.produitservice.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.ipsas.coremodels.models.produit.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
