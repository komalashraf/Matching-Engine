package engine.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class OrderTypeTest {
    @Test
    void testFromString() {
        Assertions.assertEquals(OrderType.BUY, OrderType.fromString("BUY"));

    }

    @Test
    void testFromStringException() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class,
                () -> OrderType.fromString("TEST"));
        Assertions.assertEquals("Unsupported value: TEST", thrown.getMessage());
    }
}
