package engine.enums;

import java.util.Arrays;

public enum OrderType {
   BUY,
   SELL;

   public static OrderType fromString(String type) {
      return Arrays.stream(OrderType.values()).filter(x -> x.name().equals(type)).findAny()
            .orElseThrow(() -> new IllegalArgumentException("Unsupported value: " + type));
   }
}
