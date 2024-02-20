package com.jacksspring.dscommerce.entity;

import jakarta.persistence.*;
import org.springframework.data.domain.Sort;

import java.util.*;

@Entity
@Table(name="tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private String imgUrl;

    @ManyToMany
    @JoinTable(
            name = "tb_product_category",
            joinColumns =@JoinColumn(name = "product_id"),
            inverseJoinColumns =  @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> itens = new HashSet<>();
    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        Id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }
    public Set<OrderItem> getOrderItem() {
        return itens;
    }

    public List<Order> getOrder() {
        return itens.stream().map(x -> x.getOrder()).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
