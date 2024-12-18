package entity;

public class Product {
    private int id;
    private String name;
    private Double price;

    public Product(int id, String name, Double price) {
        if (id <= 0) throw new IllegalArgumentException("Id must be more than 0");
        if (name == null || name.length() > 100) throw new IllegalArgumentException("Name cannot be null or more than 100 characters.");
        if (price == null || price <= 0) throw new IllegalArgumentException("Price must be more than zero.");

        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("Id must be more than 0");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() > 100) throw new IllegalArgumentException("Name cannot be null or more than 100 characters.");
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price == null || price <= 0) throw new IllegalArgumentException("Price must be more than zero.");
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}