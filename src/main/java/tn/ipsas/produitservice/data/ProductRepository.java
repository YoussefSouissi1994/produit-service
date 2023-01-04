package tn.ipsas.produitservice.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.ipsas.coremodels.models.produit.Product;

public interface ProductRepository extends MongoRepository <Product, String> {

}
