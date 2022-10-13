package engine.controller;

import java.io.BufferedInputStream;
import java.util.Scanner;
import java.util.logging.Logger;

import engine.service.TradeService;

public class TradeController {
    private static final Logger LOGGER = Logger.getLogger(TradeController.class.getName());
    private TradeService service = TradeService.getInstance();

    public void startTrading() {
        LOGGER.info("--------- Trading Process Started ---------------");
        LOGGER.info("........ Enter Input Data .........");

        try (Scanner stdin = new Scanner(new BufferedInputStream(System.in))) {
            String line;
            while (!(line = stdin.nextLine()).equals("")) {
                service.tradeOrder(line);
            }
            service.printOrders();
        } catch (Exception e) {
            LOGGER.info("Exception in Trading Controller " + e);
        }
    }

}
