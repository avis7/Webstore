package Store;

public class OrderItem {
private int price;
private int amount;
private Product product;
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
@Override
public String toString() {
	return "OrderItem [price=" + price + ", amount=" + amount + ", product="
			+ product + "]";
}


}
