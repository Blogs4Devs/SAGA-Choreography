package productservice.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import productservice.demo.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    /*
    * Here you can specify other JPA methods if needed
    * */
}

