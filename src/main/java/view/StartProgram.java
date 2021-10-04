package view;

/**
 * @author krebs jjkrebs1
 * CIS 175 - Fall 2021
 * Sep 16, 2021
 */

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import controller.groceryHelper;

import model.groceryItem;


public class StartProgram {
	static EntityManagerFactory emfactory = 
			Persistence.createEntityManagerFactory("GroceryList");

		static Scanner in = new Scanner(System.in);
		static groceryHelper gh = new groceryHelper();

		private static void addAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter a type: ");
			String type = in.nextLine();
			System.out.print("Enter an item: ");
			String item = in.nextLine();
			groceryItem toAdd = new groceryItem(type, item);
			gh.insertItem(toAdd);

		}
		private static void deleteAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter the type to delete: ");
			String type = in.nextLine();
			System.out.print("Enter the item to delete: ");
			String item = in.nextLine();
			groceryItem toDelete = new groceryItem(type, item);
			gh.deleteItem(toDelete);

		}
		
		private static void editAnItem() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Type");
			System.out.println("2 : Search by Item");
			int searchBy = in.nextInt();
			in.nextLine();
			List<groceryItem> foundItems;
			if (searchBy == 1) {
				System.out.print("Enter the type: ");
				String type = in.nextLine();
				foundItems = gh.searchForItemByType(type);
			} else {
				System.out.print("Enter the item: ");
				String itemName = in.nextLine();
				foundItems = gh.searchForItemByItem(itemName);

			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (groceryItem l : foundItems) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				groceryItem toEdit = gh.searchForItemById(idToEdit);
				System.out.println("Retrieved " + toEdit.getItem() + " from " + toEdit.getType());
				System.out.println("1 : Update Type");
				System.out.println("2 : Update Item");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Type: ");
					String newType = in.nextLine();
					toEdit.setType(newType);
				} else if (update == 2) {
					System.out.print("New Item: ");
					String newItem = in.nextLine();
					toEdit.setItem(newItem);
				}

				gh.updateItem(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to the grocery list! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add an item");
				System.out.println("*  2 -- Edit an item");
				System.out.println("*  3 -- Delete an item");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					gh.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}
		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<groceryItem> allItems = gh.showAllItems();
			for(groceryItem singleItem : allItems) {
				System.out.println(singleItem.returnItemDetails());
			}
			

		}
		
		public List<groceryItem>searchForItemByType(String type){
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<groceryItem>typedQuery = em.createQuery("Select li from groceryItem li where li.type = :selectedType", groceryItem.class);
			typedQuery.setParameter("selectedType", type);
			
			List<groceryItem>foundItems = typedQuery.getResultList();
			em.close();
			return foundItems;
			
		}
		public List<groceryItem>searchForItemByItem(String itemName){
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<groceryItem>typedQuery = em.createQuery("select li from groceryItem li where li.groceryItem = :selectedGroceryItem", groceryItem.class);
			typedQuery.setParameter("selectedItem", itemName);
			
			List<groceryItem> foundItems = typedQuery.getResultList();
			em.close();
			return foundItems;
		}
		public groceryItem searchForItemById(int idToEdit) {
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			groceryItem found = em.find(groceryItem.class, idToEdit);
			em.close();
			return found;
		}
}
