package Store;

import java.util.ArrayList;

import dao.ProductsDAO;

public class ProductList {
	private static ProductList instance;
	private static ArrayList<Product> productList;
	private static ProductsDAO productBase = new ProductsDAO();
	public static ProductList getInstance(){
        if (instance == null){
                instance = new ProductList();
                productList = new ArrayList<Product>(); 
        }
        return instance;
}
	public static void addProduct(Product product) {
		productBase.insertNewRecord(product);
			}
	public static ArrayList<Product> getProductList() {
		productList=productBase.findAll();
		return productList;
	}
		
}
