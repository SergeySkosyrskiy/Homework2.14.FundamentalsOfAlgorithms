package org.example.service;
import org.example.exception.InvalidIndexException;
import org.example.exception.NullItemException;
import org.example.exception.StorageIsFullException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringListImplTest {
    private StringListImpl list;
    @Before
    public void setUp() {
        list = new StringListImpl(5);
    }

    @Test
    public void testAdd() {
        list.add("Test");
        assertEquals("Test", list.get(0));
    }

    @Test(expected = NullItemException.class)
    public void testAddNull() {
        list.add(null);
    }

    @Test(expected = StorageIsFullException.class)
    public void testAddWhenFull() {
        for (int i = 0; i < 5; i++) {
            list.add("Test" + i);
        }
        list.add("Overflow");
    }

    @Test
    public void testAddAtIndex() {
        list.add("First");
        list.add("Second");
        list.add(1, "Between");
        assertEquals("Between", list.get(1));
    }

    @Test(expected = InvalidIndexException.class)
    public void testAddAtInvalidIndex() {
        list.add(5, "Invalid");
    }

    @Test
    public void testSet() {
        list.add("First");
        list.set(0, "Changed");
        assertEquals("Changed", list.get(0));
    }

    @Test(expected = InvalidIndexException.class)
    public void testSetAtInvalidIndex() {
        list.set(0, "Nothing");
    }

    @Test
    public void testRemoveByItem() {
        list.add("RemoveMe");
        list.remove("RemoveMe");
        assertEquals(0, list.size());
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveNonExistingItem() {
        list.remove("NonExisting");
    }

    @Test
    public void testRemoveByIndex() {
        list.add("RemoveMe");
        list.remove(0);
        assertEquals(0, list.size());
    }

    @Test(expected = InvalidIndexException.class)
    public void testRemoveAtInvalidIndex() {
        list.remove(0);
    }

    @Test
    public void testContains() {
        list.add("FindMe");
        assertTrue(list.contains("FindMe"));
    }

    @Test
    public void testIndexOf() {
        list.add("FindMe");
        assertEquals(0, list.indexOf("FindMe"));
    }

    @Test
    public void testLastIndexOf() {
        list.add("FindMe");
        list.add("FindMe");
        assertEquals(1, list.lastIndexOf("FindMe"));
    }

    @Test
    public void testGet() {
        list.add("GetMe");
        assertEquals("GetMe", list.get(0));
    }

    @Test(expected = InvalidIndexException.class)
    public void testGetAtInvalidIndex() {
        list.get(0);
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());
        list.add("One");
        assertEquals(1, list.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("Not empty anymore");
        assertFalse(list.isEmpty());
    }

    @Test
    public void testClear() {
        list.add("ClearMe");
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testToArray() {
        list.add("One");
        list.add("Two");
        String[] expectedArray = new String[] {"One", "Two"};
        assertArrayEquals(expectedArray, list.toArray());
    }
}
