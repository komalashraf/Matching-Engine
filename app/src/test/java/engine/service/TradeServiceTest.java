package engine.service;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.junit.platform.commons.util.ReflectionUtils.HierarchyTraversalMode;

import engine.model.Buy;
import engine.model.Order;

public final class TradeServiceTest {

        private TradeService tradeService = TradeService.getInstance();

        @Test
        public void whenNonPublicMethod_thenReflectionTestUtilsInvokeMethod2()
                        throws NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalArgumentException,
                        IllegalAccessException {

                Field contextField = ReflectionUtils
                                .findFields(TradeService.class, f -> f.getName().equals("buyerOrders"),
                                                HierarchyTraversalMode.TOP_DOWN)
                                .stream().findFirst().orElseThrow(() -> new NoSuchFieldException("buyerOrders"));
                ReflectionUtils.makeAccessible(contextField);
                Set<Order> orders = new HashSet<>();

                Order order = new Buy("12345", "BTCUSD", 5, Long.valueOf(10000));
                orders.add(order);

                order = new Buy("11431", "BTCUSD", 6, Long.valueOf(9971));
                orders.add(order);

                Assertions.assertEquals(2, orders.size());
                contextField.set(tradeService, orders);

                String str = "12543 SELL BTCUSD 7 9800";

                Class<?>[] method_arguments = new Class[1];
                method_arguments[0] = String.class;

                ReflectionUtils
                                .invokeMethod(
                                                tradeService.getClass().getDeclaredMethod("tradeOrder",
                                                                method_arguments),
                                                tradeService, str);

                Field contextField2 = ReflectionUtils
                                .findFields(TradeService.class, f -> f.getName().equals("tradedOrders"),
                                                HierarchyTraversalMode.TOP_DOWN)
                                .stream().findFirst()
                                .orElseThrow(() -> new NoSuchFieldException("tradedOrders"));
                ReflectionUtils.makeAccessible(contextField2);

                List<?> tradedOrders = (List<?>) contextField2.get(tradeService);
                Assertions.assertEquals(2, tradedOrders.size());

                Set<?> buyerData = (Set<?>) contextField.get(tradeService);
                Assertions.assertEquals(1, buyerData.size());

                Field sellerOrdersField = ReflectionUtils
                                .findFields(TradeService.class, f -> f.getName().equals("sellerOrders"),
                                                HierarchyTraversalMode.TOP_DOWN)
                                .stream().findFirst()
                                .orElseThrow(() -> new NoSuchFieldException("sellerOrders"));
                ReflectionUtils.makeAccessible(sellerOrdersField);

                Set<?> sellerData = (Set<?>) sellerOrdersField.get(tradeService);
                Assertions.assertEquals(0, sellerData.size());
        }
}
