/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package engine;

import engine.controller.TradeController;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        TradeController c = new TradeController();
        c.startTrading();
    }
}
