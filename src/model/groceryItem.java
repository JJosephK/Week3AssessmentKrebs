package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author krebs jjkrebs1
 * CIS 175 - Fall 2021
 * Sep 16, 2021
 */
@Entity
@Table(name = "groceries")
public class groceryItem {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="TYPE")
	private String type;
	@Column(name="ITEM")
	private String item;
	
	public groceryItem() {
		super();
	}
	public groceryItem(String type, String item) {
		this.type = type;
		this.item = item;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}
	
	public String returnItemDetails() {
		return this.type + ": " + this.item;
	}
}
