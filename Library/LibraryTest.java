package Library;

import Items.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LibraryTest {
    Library libraryTest;

    private Item itemAvailable;
    private Item itemCheckedOut;
    private Item itemNotAvailableAndNotCheckedOut;
    private Item itemBook;
    private Item itemMagazine;
    private Item itemCd;
    private Item itemDvd;

    @Before
    public void setUp() throws Exception {

        libraryTest = new Library(Library.Type.MAIN);

        itemAvailable = new Item("itemAvailable", "item1", Item.Type.BOOK);
        itemAvailable.setAvailable(true);
        libraryTest.addItem(itemAvailable);

        itemCheckedOut = new Item("itemCheckedOut", "item2", Item.Type.BOOK);
        itemCheckedOut.setAvailable(false);
        libraryTest.addItem(itemCheckedOut);

        itemBook = new Item("Book1", "Book1", Item.Type.BOOK);
        libraryTest.addItem(itemBook);
        itemMagazine = new Item("Magazine1", "Magazine1", Item.Type.MAGAZINE);
        libraryTest.addItem(itemMagazine);
        itemCd = new Item("CD1", "CD1", Item.Type.CD);
        libraryTest.addItem(itemCd);
        itemDvd = new Item("DVD1", "DVD1", Item.Type.DVD);
        libraryTest.addItem(itemDvd);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void checkOut() throws Exception {
        assertEquals(null, libraryTest.checkOut("BadItemID"));
        assertEquals(false, libraryTest.checkOut("itemCheckedOut"));
        assertEquals(true, libraryTest.checkOut("itemAvailable"));
    }

    @Test
    public void checkIn() throws Exception {
        assertEquals(null, libraryTest.checkIn("BadItemID"));
        assertEquals(false, libraryTest.checkIn("itemAvailable"));
        assertEquals(true, libraryTest.checkIn("itemCheckedOut"));
    }

    @Test
    public void displayItemsOfType() throws Exception {
        String response = "";
        response = libraryTest.displayItemsOfType(Item.Type.BOOK);
        assertTrue(response.contains("Book1"));
        response = libraryTest.displayItemsOfType(Item.Type.MAGAZINE);
        assertTrue(response.contains("Magazine1"));
        response = libraryTest.displayItemsOfType(Item.Type.CD);
        assertTrue(response.contains("CD1"));
        response = libraryTest.displayItemsOfType(Item.Type.DVD);
        assertTrue(response.contains("DVD1"));

    }

}