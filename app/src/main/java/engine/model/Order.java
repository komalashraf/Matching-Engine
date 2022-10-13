package engine.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.annotation.Nonnull;

import engine.enums.OrderType;

/**
 * Represents the order received by customer
 * 
 * @author Komal Ashraf
 * @since Oct 03, 2022
 */
public class Order {
  
    private String orderId;
    private String instrument;
    @Nonnull
    private Integer quantity;
    private Long price;
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private OrderType orderType;

    /**
     * Order Constructor
     * 
     * @param orderId    the order Id
     * @param instrument the order name
     * @param quantity   the order quantity
     * @param price      the price of order
     * @param orderType  the orderType e.g; BUY, SELL
     */
    public Order(String orderId, String instrument, Integer quantity, Long price, OrderType orderType) {
        this.orderId = orderId;
        this.instrument = instrument.trim();
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
    }

    /**
     * Returns the order
     * 
     * @return String the order
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * Sets the order
     * 
     * @param orderId the orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Returns the order name e.g BTCUSD
     * 
     * @return String the instrument
     */
    public String getInstrument() {
        return this.instrument;
    }

    /**
     * Sets the order name e.g BTCUSD
     * 
     * @param instrument the type of currency
     */
    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    /**
     * Returns the number of orders
     * 
     * @return Integer the quatity
     */
    public Integer getQuantity() {
        return this.quantity;
    }

    /**
     * Set the number of items in order
     * 
     * @param quantity the order quatity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the total price of a order
     * 
     * @return Long the price
     */
    public Long getPrice() {
        return this.price;
    }

    /**
     * Set the total price of a order
     * 
     * @param price
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * Returns the time when order is received
     * 
     * @return LocalDateTime
     */
    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }

    public OrderType getOrderType() {
        return this.orderType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}
