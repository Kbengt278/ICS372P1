package Library;


import Items.Item;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Creates and maintains a list of items at this library.
 * Allows items to be added to it via JSON or XML files.
 * Allows items to be checked out and checked in.
 * Allows all items in the library catalog to be displayed by type.
 */
public class Library implements Serializable {

    private HashMap<String, Item> itemList = new HashMap<>();
    private Type libraryType = null;

	public Library(Type type) {
    	this.libraryType = type;
    }

    /**
     * handles checking out an item
     * sets the item's available flag false
     * sets the item's setDateDue to current date plus item's checkout time
     *
     * @param itemId id of the item to check out
     * @return null if item does not exist, false if item is not available, otherwise true
     */
    public Boolean checkOut(String itemId) {
        Item item = this.getItem(itemId);
        if (item == null) {
            return null;
        } else if (!item.isAvailable()) {
            return false;
        } else {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, item.getCheckOutTimeDays());
            item.setAvailable(false);
            item.setDateDue(cal);
            return true;
        }
    }

    /**
     * handles checking in an item
     * sets the item's available flag true
     * sets the item's setDateDue to null
     *
     * @param itemId id of the item to check in
     * @return null if item does not exist, false if item is available, otherwise true
     */
    public Boolean checkIn(String itemId) {
        Item item = itemList.get(itemId);
        if (item == null) {
            return null;
        } else if (item.isAvailable()) {
            return false;
        } else {
            item.setAvailable(true);
            item.setDateDue(null);
            return true;
        }
    }

    /**
     * Displays items of given type in this Library's catalog.
     *
     * @param type the type of the items to display
     * @return text to display to user
     */
    public String displayItemsOfType(Item.Type type) {
        String message = "";
        for (Item value : itemList.values()) {
            if (type.equals(value.getType())) {
                message += "\n" + value.toString();
            }
        }
        return message;
    }

    /**
     * Gets item from the list of items in this library.
     *
     * @param id item to get
     * @return the item object
     */
    public Item getItem(String id) {
        return itemList.get(id);
    }
    
    /**
     * Gets library type (main or sister)
     * @return Main or Sister
     */
    public Type getLibraryType() {
		return libraryType;
	}
    
    /**
     * Adds item to the list of items in this library.
     *
     * @param item The item object to be added to list
     **/
    public void addItem(Item item) {
        itemList.put(item.getId(), item);
    }

    public int size() {
        return itemList.size();
    }

    public enum Type {
        MAIN, SISTER
    }
}
