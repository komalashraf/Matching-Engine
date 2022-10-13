package engine.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import engine.enums.TradeType;

public class TradeTest {

    @Test
    void testSeller() {
        Trade order = new Trade("BCUSD", "1234", "54367", 1, 1000L);

        Assertions.assertEquals("1234", order.getOrderId());
        Assertions.assertEquals("54367", order.getContraOrderId());
        Assertions.assertEquals("BCUSD", order.getInstrument());
        Assertions.assertEquals(1, order.getQuantity());
        Assertions.assertEquals(1000L, order.getPrice());
        Assertions.assertEquals(null, order.getOrderType());
        Assertions.assertEquals(TradeType.TRADE, order.getTradeType());
        Assertions.assertNotNull(order.getTimeStamp());
    }
}
