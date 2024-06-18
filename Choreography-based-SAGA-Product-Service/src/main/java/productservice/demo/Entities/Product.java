package productservice.demo.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Product label must be defined")
    private String label;

    @NotNull(message = "Price must be defined")
    private double price;

    @NotNull(message = "Stock must be defined")
    private int stock;

    @NotNull(message = "Description must be defined")
    private String description;

    public Product() {
    }

    public Product(String label, double price, int stock, String description) {
        this.label = label;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() { return stock; }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) { this.stock = stock; }

    public void setDescription(String description) {
        this.description = description;
    }

}
