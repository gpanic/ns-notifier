package com.nileshop.notifier.android.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.nileshop.notifier.android.entity.Category;
import com.nileshop.notifier.android.entity.Product;

public class NSParser {
	
	public static List<Product> productsFromJSON(String json) {
		List<Product> products = new ArrayList<Product>();
		try {
			JSONArray productsJSON = new JSONArray(json);
			for (int i = 0; i < productsJSON.length(); i++) {
				JSONObject productJSON = productsJSON.getJSONObject(i);
				Product productObj = productFromJSON(productJSON.toString());
				products.add(productObj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public static Product productFromJSON(String json) {
		try {
			JSONObject productJSON = new JSONObject(json);
			Product product = new Product();
			product.setId(productJSON.getInt("id"));
			product.setCategory(categoryFromJSON(productJSON.getJSONObject("category").toString()));
			product.setDescription(productJSON.getString("description"));
			product.setImage(productJSON.getString("image"));
			product.setManufacturer(productJSON.getString("manufacturer"));
			product.setName(productJSON.getString("name"));
			product.setPrice(productJSON.getDouble("price"));
			product.setWeight(productJSON.getDouble("weight"));
			product.setAdded(new Date(productJSON.getLong("added")));
			return product;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Category> categoriesFromJSON(String json) {
		List<Category> categories = new ArrayList<Category>();
		try {
			JSONArray categoriesJSON = new JSONArray(json);
			for (int i = 0; i < categoriesJSON.length(); i++) {
				JSONObject categoryJSON = categoriesJSON.getJSONObject(i);
				Category categoryObj = categoryFromJSON(categoryJSON.toString());
				categories.add(categoryObj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	public static Category categoryFromJSON(String json) {
		try {
			JSONObject categoryJSON = new JSONObject(json);
			Category category= new Category();
			category.setId(categoryJSON.getInt("id"));
			category.setName(categoryJSON.getString("name"));
			return category;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
