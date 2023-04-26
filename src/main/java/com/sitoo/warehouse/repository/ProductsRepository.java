package com.sitoo.warehouse.repository;

import com.sitoo.warehouse.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Long>, CrudRepository<Products,Long>, PagingAndSortingRepository<Products, Long> {

    @Query(value = "SELECT * FROM products LIMIT :indexFrom,:indexRange", nativeQuery = true)
    List<Products> findTopN(@Param("indexFrom") int indexFrom, @Param("indexRange") int indexRange);
    Products findBySku(String sku);
    Products findByBarcode(String barcodes);
}
