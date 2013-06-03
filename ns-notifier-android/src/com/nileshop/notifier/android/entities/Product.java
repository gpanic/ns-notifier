package com.nileshop.notifier.android.entities;

public class Product {
	
    private int id;
    private String name;
    private double price;
    private String description;
    private double weight;
    private String manufacturer;
    private String image;
    
    public Product() {
    }

    public Product(String name, double price, String description, double weight, String manufacturer, Category category, String image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.weight = weight;
        this.manufacturer = manufacturer;
        this.category = category;
        this.image = image;
    }
    
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", description=" + description + ", weight=" + weight
				+ ", manufacturer=" + manufacturer + ", image=" + image
				+ ", category=" + category + "]";
	}

}
