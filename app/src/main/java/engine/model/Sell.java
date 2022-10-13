package engine.model;

import java.util.StringJoiner;

import engine.enums.OrderType;

public class Sell extends Order {

    public Sell(String orderId, String instrument, Integer quantity, Long price) {
        super(orderId, instrument, quantity, price, OrderType.SELL);
    }

    @Override
    public String toString() {
        return new StringJoiner("       ")
                .add(this.getOrderId())
                .add(this.getOrderType().name())
                .add(this.getInstrument())
                .add(String.valueOf(this.getQuantity()))
                .add(String.valueOf(this.getPrice()))
                .toString();
    }
}
