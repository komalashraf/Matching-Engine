package engine.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import  engine.service.RegistrationService;

import engine.enums.OrderType;
import engine.model.Buy;
import engine.model.Order;
import engine.model.Trade;
import engine.model.Sell;

public class TradeService implements RegistrationService{
    public static final Logger LOGGER = Logger.getLogger(TradeService.class.getName());
    private static TradeService tradeService = new TradeService();
    private Set<Order> buyerOrders = new HashSet<>();
    private Set<Order> sellerOrders = new HashSet<>();
    private List<Order> processedSellerOrders = new ArrayList<>();
    private List<Trade> tradedOrders = new ArrayList<>();

    private TradeService() {

    }

    public static TradeService getInstance() {
        return tradeService;
    }

    public void tradeOrder(String data) {
        Optional<Order> optionalOrder = convertStringToOrder(data);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            switch (order.getOrderType()) {
                case BUY:
                    tradeBuyer(order, this.buyerOrders, this.sellerOrders);
                    break;

                case SELL:
                    tradeSeller(order, this.buyerOrders, this.sellerOrders);
                    break;
                

                default:
                   
                    break;
            }
        }
    }

    private Optional<Order> convertStringToOrder(String data) {

        String[] object = Optional.ofNullable(data).isPresent() ? data.trim().replaceAll("\\s+", " ").split("\\s")
                : new String[0];

        if (object.length > 0) {
            if (OrderType.BUY.name().equals(object[1])) {
                return Optional.of(new Buy(object[0], object[2], Integer.valueOf(object[3].trim()),
                        Long.valueOf(object[4].trim())));
            } else {
                return Optional.of(new Sell(object[0], object[2], Integer.valueOf(object[3].trim()),
                        Long.valueOf(object[4].trim())));
            }
        }
        return Optional.empty();
    }

    private void createTradedOrder(Order buyerOrder, Order sellerOrder) {
        this.tradedOrders.add(
                new Trade(buyerOrder.getInstrument(),
                        buyerOrder.getOrderId(), sellerOrder.getOrderId(),
                        buyerOrder.getQuantity(), buyerOrder.getPrice()));
    }

    private void tradeBuyer(Order order, Set<Order> buyerOrderSet, Set<Order> sellerOrderSet) {
        List<Order> sellerOrderList = sellerOrderSet.stream()
                .filter(sellerOrder -> sellerOrder.getInstrument().toLowerCase()
                        .equals(order.getInstrument().toLowerCase()))
                .filter(sellerOrder -> sellerOrder.getPrice() <= order.getPrice())
                .sorted(Comparator.comparing(Order::getPrice).thenComparing(Order::getTimeStamp))
                .collect(Collectors.toList());

        if (sellerOrderList.size() == 0) {
            buyerOrderSet.add(order);
            return;
        }

        for (Order seller : sellerOrderList) {

            int buyerQuantity = order.getQuantity() - seller.getQuantity();
            int sellerQuantity = seller.getQuantity() - order.getQuantity();

            if (sellerQuantity > 0) {
                seller.setQuantity(sellerQuantity);
                sellerOrderSet.add(seller);
            } else {
                sellerOrderSet.remove(seller);
                processedSellerOrders.add(seller);
            }

            if (buyerQuantity > 0) {
                order.setQuantity(order.getQuantity() - buyerQuantity);
                createTradedOrder(order, seller);

                order.setQuantity(buyerQuantity);
                buyerOrderSet.add(order);
            } else {
                buyerOrderSet.remove(order);
                createTradedOrder(order, seller);
                break;
            }

        }
    }

    private void tradeSeller(Order sellerOrder, Set<Order> buyerOrderSet, Set<Order> sellerOrderSet) {
        List<Order> buyerOrderList = buyerOrderSet.stream()
                .filter(buyer -> buyer.getInstrument().toLowerCase()
                        .equals(sellerOrder.getInstrument().toLowerCase()))
                .filter(buyer -> sellerOrder.getPrice() <= buyer.getPrice())
                .sorted(Comparator.comparing(Order::getPrice).reversed().thenComparing(Order::getTimeStamp))
                .collect(Collectors.toList());

        if (buyerOrderList.size() == 0) {
            sellerOrderSet.add(sellerOrder);
            return;
        }
        for (Order buyer : buyerOrderList) {

            int buyerQuantity = buyer.getQuantity() - sellerOrder.getQuantity();
            int sellerQuantity = sellerOrder.getQuantity() - buyer.getQuantity();

            if (buyerQuantity > 0) {
                buyer.setQuantity(buyer.getQuantity() - buyerQuantity);
                createTradedOrder(buyer, sellerOrder);

                buyer.setQuantity(buyerQuantity);
                buyerOrderSet.add(buyer);
            } else {
                buyerOrderSet.remove(buyer);
                createTradedOrder(buyer, sellerOrder);
            }

            if (sellerQuantity > 0) {
                sellerOrder.setQuantity(sellerQuantity);
                sellerOrderSet.add(sellerOrder);
            } else {
                sellerOrderSet.remove(sellerOrder);
                processedSellerOrders.add(sellerOrder);
                break;
            }
        }
    }

    public void printOrders() {
        tradedOrders.forEach(System.out::println);
        System.out.println();
        this.sellerOrders.stream().forEach(System.out::println);
        this.buyerOrders.stream().forEach(System.out::println);
    }

    

    @Override
    public void unregister(Long id) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Buy register(Buy customer) throws Exception {
       
        try{

        } catch(Throwable e) {
            
        }
        return null;
    }
}