package com.trapped.player;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class InventoryTest {
    private Inventory inventory;

    @Before
    public void setUp(){
        inventory = Inventory.getInstance();
    }

    @Test
    public void additem_willAddItemToInventory_andReturnUpdatedInvList() {
        assertNotNull(inventory.getInvList());
        List<String> emptyTest = new ArrayList<>();
        assertEquals(inventory.getInvList(), emptyTest);

        inventory.addItem("wallet");
        inventory.addItem("crowbar");
        assertTrue(inventory.getInvList().contains("wallet"));
        assertTrue(inventory.getInvList().contains("crowbar"));

        List<String> containsTest = new ArrayList<>();
        containsTest.add("wallet");
        containsTest.add("crowbar");

        assertEquals(inventory.getInvList(), containsTest);
    }

    @Test
    public void hasItem_returnsTrue_ifInventoryHasItem() {
        inventory.addItem("matches");
        assertTrue(inventory.hasItem("matches"));
    }

    @Test
    public void hasItem_returnsFalse_ifInventoryDOESNOTHaveItem() {
        assertFalse(inventory.hasItem("nothing"));
        inventory.addItem("key2");
        assertFalse(inventory.hasItem("key"));
    }
}