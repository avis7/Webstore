package Store;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
	private Date date;
	private int id;
	private ArrayList<OrderItem> items;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<OrderItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<OrderItem> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Order [date=" + date + ", id=" + id + ", items=" + items + "]";
	}
	
	
}
