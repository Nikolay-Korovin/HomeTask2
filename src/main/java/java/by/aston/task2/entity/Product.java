package java.by.aston.task2.entity;

import javax.persistence.*;

@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "productname")
    private String productName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product(Long id, String productName, User user) {
        this.id = id;
        this.productName = productName;
        this.user = user;
    }

    public Product(String productName, User user) {
        this.productName = productName;
        this.user = user;
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public Product() {
    }


}