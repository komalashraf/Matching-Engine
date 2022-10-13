# Matching Engine

The matching engine will take stream of input data that contains buy order, sell order and will initiate trade orders.

---
---

### Technologies used
* Java 8
* Gradle 7.5.1


### Instructions on how to build and run your application
* ./gradlew clean build run

### How problem is approached

*  Once engine will receive input stream. It will convert each line of input stream to order object.
*  Once order object is created then engine will determine either it is BUY order or SELL order and will initiate trading.
*  If order is buy order then engine will pick up all the sell orders whose price is equal or less than buyer ask, sort orders from lowest sell order to highest sell order and then by timestamp.
* If order is sell order then engine will pick up all the buy orders, sort orders from higher buy price to lower buy price and then by time stamp.
* Engine will update buy order quantity and sell order quantity based on trading.
* Once input stream is consumed completely then engine will print traded orders, buy orders if left any and sell orders if left any.

### How much time Spent

* 2 hours for coding and testing
* Extra 15 minutes for writing unit test for TradeService as I never used reflections earlier so it took me little extra time.