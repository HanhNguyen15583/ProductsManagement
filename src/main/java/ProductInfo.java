public class ProductInfo {
    String id;
    String name;
    Integer quantity;
    float price;
    ProductInfo() {}
    ProductInfo(String id, String name, Integer quantity, float price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}
    public void setPrice(float price) {this.price = price;}

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    /**
     * Hiển thị thông tin sản phẩm
     * @return
     */
    public String toString() {
        return (getId() + "     " + getName() + "     " + getQuantity() + "     " + getPrice());
    }
}