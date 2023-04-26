package com.sitoo.warehouse.models;

import org.jetbrains.annotations.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name="products",
        indexes = {
                @Index(name = "created", columnList = "created"),
                @Index(name = "product_id", columnList = "product_id"),
                @Index(name = "sku", columnList = "sku")})
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;

    private String title;

    @Column(unique=true)
    private String sku;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Barcode> barcodes = new ArrayList<>();

    private String description;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Attributes> attributes = new ArrayList<>();

    @Column(name="price", columnDefinition="Decimal(10,2) default '0.00'")
    private double price;

    @Column(name="created",nullable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private java.util.Date created;



    public Products(long productId, String title, String sku, List<Barcode> barcodes, String description, List<Attributes> attributes, double price) {
        this.productId = productId;
        this.title = title;
        this.sku = sku;
        this.barcodes = barcodes;
        this.description = description;
        this.attributes = attributes;
        this.price = price;
    }

    public Products() {
    }

    public long getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public String getSku() {
        return sku;
    }

    public List<Barcode> getBarcodes() {
        return barcodes;
    }

    public String getDescription() {
        return description;
    }

    public List<Attributes> getAttributes() {
        return attributes;
    }

    public double getPrice() {
        return price;
    }

    public Date getCreated() {
        return created;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setBarcodes(List<Barcode> barcodes) {
        this.barcodes = barcodes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAttributes(List<Attributes> attributes) {
        this.attributes = attributes;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Products products = (Products) o;
        return productId == products.productId && Double.compare(products.price, price) == 0 && Objects.equals(title, products.title) && Objects.equals(sku, products.sku) && Objects.equals(barcodes, products.barcodes) && Objects.equals(description, products.description) && Objects.equals(attributes, products.attributes) && Objects.equals(created, products.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, title, sku, barcodes, description, attributes, price, created);
    }

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", title='" + title + '\'' +
                ", sku='" + sku + '\'' +
                ", barcodes=" + barcodes +
                ", description='" + description + '\'' +
                ", attributes=" + attributes +
                ", price=" + price +
                ", created=" + created +
                '}';
    }
}
