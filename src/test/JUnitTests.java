package test;

import main.java.Camping.ReservationID;
import main.java.Exceptions.InvalidReservationIDException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JUnitTests {

    ReservationID RID1 = new ReservationID("000000");
    ReservationID RID2 = new ReservationID("000001");

    ReservationID RID3 = new ReservationID("00000Z");
    ReservationID RID4 = new ReservationID("000010");

    ReservationID RID5 = new ReservationID("000ZZZ");
    ReservationID RID6 = new ReservationID("001000");


    @Test
    public void testEdgeFromID() throws InvalidReservationIDException {
        assertEquals(RID2.getAlphanumericCode(), RID1.plusOne().getAlphanumericCode());
    }

    @Test
    public void testEdgeFromID1() throws InvalidReservationIDException {
        assertEquals(RID4.getAlphanumericCode(), RID3.plusOne().getAlphanumericCode());
    }

    @Test
    public void testEdgeFromID2() throws InvalidReservationIDException {
        assertEquals(RID6.getAlphanumericCode(), RID5.plusOne().getAlphanumericCode());
    }

}
