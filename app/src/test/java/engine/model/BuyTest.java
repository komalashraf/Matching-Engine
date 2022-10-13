package engine.model;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import engine.enums.OrderType;

public class BuyTest {
    @Test
    void testBuyer() {
    //     Buy order = new Buy("1234", "BCUSD", 1, 1000L);

    //     Assertions.assertEquals("1234", order.getOrderId());
    //     Assertions.assertEquals("BCUSD", order.getInstrument());
    //     Assertions.assertEquals(1, order.getQuantity());
    //     Assertions.assertEquals(1000L, order.getPrice());
    //     Assertions.assertEquals(OrderType.BUY, order.getOrderType());
    //     Assertions.assertNotNull(order.getTimeStamp());


       Optional<Buy> order = Optional.ofNullable(new Buy(null, "BCUSD", null, 1000L));

        Integer r = order.isPresent() ? order.get().getQuantity() : 23;
        System.out.print(" ------" + r);
    }
}
