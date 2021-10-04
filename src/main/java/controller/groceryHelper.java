package controller;

/**
 * @author krebs jjkrebs1
 * CIS 175 - Fall 2021
 * Sep 16, 2021
 */

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import model.groceryItem;
public class groceryHelper {
	static EntityManagerFactory emfactory = 
			Persistence.createEntityManagerFactory("GroceryList");

	public void insertItem(groceryItem li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<groceryItem>showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<groceryItem> allItems = em.createQuery("Select i FROM groceryItem i").getResultList();
		return allItems;
	}
	
	public void deleteItem(groceryItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<groceryItem>typedQuery = em.createQuery("select li from groceryItem li where li.type = :selectedType and li.item = :selectedItem", groceryItem.class);
		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedType", toDelete.getType());
		typedQuery.setParameter("selectedItem", toDelete.getItem());
		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		groceryItem result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * @param idToEdit
	 * @return
	 */
	public groceryItem searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager(); 
		em.getTransaction().begin();
		
		groceryItem found = em.find(groceryItem.class, idToEdit);
		em.close();
		return found;
	}

	/**
	 * @param toEdit
	 */
	public void updateItem(groceryItem toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param type
	 * @return
	 */
	public List<groceryItem> searchForItemByType(String type) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<groceryItem> typedQuery = em.createQuery("select li from ListItem li where li.store = :selectedStore", groceryItem.class);
		
		typedQuery.setParameter("selectedType", type);
		
		List<groceryItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	/**
	 * @param itemName
	 * @return
	 */
	public List<groceryItem> searchForItemByItem(String item) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<groceryItem> typedQuery = em.createQuery("select li from ListItem li where li.item = :selectedItem", groceryItem.class);
		
		typedQuery.setParameter("selectedItem", item);
		
		List<groceryItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
	
}
