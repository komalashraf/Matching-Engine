package engine.model;

import java.util.StringJoiner;

import engine.enums.TradeType;

public class Trade extends Order {
    private String contraOrderId;
    private final TradeType tradeType = TradeType.TRADE;

    public Trade(String instrument, String orderId, String contraOrderId, Integer quantity, Long price) {
        super(orderId, instrument, quantity, price, null);
        this.contraOrderId = contraOrderId;
    }

    public String getContraOrderId() {
        return this.contraOrderId;
    }

    public void setContraOrderId(String contraOrderId) {
        this.contraOrderId = contraOrderId;
    }

    public TradeType getTradeType() {
        return this.tradeType;
    }

    @Override
    public String toString() {
        return new StringJoiner("      ")
                .add(this.tradeType.name())
                .add(this.getInstrument())
                .add(this.getContraOrderId())
                .add(this.getOrderId())
                .add(String.valueOf(this.getQuantity()))
                .add(String.valueOf(this.getPrice()))
                .toString().trim();
    }
}
