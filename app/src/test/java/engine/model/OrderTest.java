package engine.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import engine.enums.OrderType;

public class OrderTest {
    @Test
    void testBOrder() {
        Order order = new Order("1234", "BCUSD", 1, 1000L, OrderType.BUY);

        Assertions.assertEquals("1234", order.getOrderId());
        Assertions.assertEquals("BCUSD", order.getInstrument());
        Assertions.assertEquals(1, order.getQuantity());
        Assertions.assertEquals(1000L, order.getPrice());
        Assertions.assertEquals(OrderType.BUY, order.getOrderType());
        Assertions.assertNotNull(order.getTimeStamp());
    }
}
