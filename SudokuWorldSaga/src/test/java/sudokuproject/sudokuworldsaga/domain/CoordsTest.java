/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.domain;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Henri
 */
public class CoordsTest {
    
    public CoordsTest() {
    }

    @Test
    public void testCoords() {
        Coords tCords = new Coords(5, 2);
        assertTrue("Coord values not set correctly", tCords.x == 5 && tCords.y == 2);
        assertTrue("ToString method not returning correct String", tCords.toString().equals("(5, 2)"));
    }
    
    @Test
    public void testCoordsComparing() {
        Coords tCords1 = new Coords(7,5);
        Coords tCords2 = new Coords(7,5);
        Coords tCords3 = new Coords(4,5);
        Coords tCords4 = new Coords(7,3);
        Coords tCords5 = new Coords(1,1);
        
        assertTrue("Should return true", tCords1.equals(tCords1));
        assertTrue("Should return true", tCords1.equals(tCords2));
        assertFalse("Should return false", tCords1.equals(tCords3));
        assertFalse("Should return false", tCords1.equals(tCords4));
        assertFalse("Should return false", tCords1.equals(tCords5));
        assertFalse("Should return false", tCords1.equals("Testi"));
        assertFalse("Should return false", tCords1.equals(null));
        
        ArrayList<Coords> list = new ArrayList<Coords>();
        Coords[] coords = {tCords1, tCords2, tCords3, tCords4, tCords5};
        for (Coords i : coords) {
            if (!list.contains(i)) {
                list.add(i);
            }
        }
        assertTrue("Should be 4 items in the list", list.size() == 4);
    }
}
