package ojles.coursework.catalogue.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    // TODO: change to TEXT or LARGETEXT
    @Column(nullable = false, length = 2048)
    private String description;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private String imagePath;

    @JoinColumn(name = "group_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ProductGroup group;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ProductParameter> parameters = new ArrayList<>();

    public Product() {
        // required by JPA
    }

    public Product(String name, String description, Long price, String imagePath, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.manufacturer = manufacturer;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public long getGroupId() {
        return group.getId();
    }

    public int getManufacturerId() {
        return manufacturer.getId();
    }

    public List<ProductParameter> getParameters() {
        return parameters;
    }

    void setGroup(ProductGroup group) {
        this.group = group;
    }
}