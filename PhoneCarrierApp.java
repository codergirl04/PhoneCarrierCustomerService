// @version1.0 08-06-2020
// @author Maya Itty
// File name: TransactionAnalyzer.java
// Program purpose: A customer service application for a wireless phone carrier
// Revision history:
// Date                  Programmer          Change ID      Description
// 08/06/2020            Maya Itty           0000           Initial implementation

import java.util.TreeMap;

public class PhoneCarrierApp {
    public static void main(String[] args) {
        // instantiate a SmartCarrier object
        SmartCarrier smartCarrier = new SmartCarrier
                (new TreeMap<>(), "Santa Clara");
        // invoke init and run methods
        smartCarrier.init();
        smartCarrier.run();
    }
}
