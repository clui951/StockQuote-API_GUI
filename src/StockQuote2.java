
public class StockQuote2 {

	static int LOOPS = 3;
	
	public static void main(String[] args) {
		System.out.println("Hello, API Investor");
		StockObj myStock = new StockObj("RHT");
		myStock.printStock();
		
//		for (int i = 0; i <= LOOPS; i++) {
//			try {
//			    Thread.sleep(5000);                 //10000 milliseconds is 5 seconds.
//			} catch(InterruptedException ex) {
//			    Thread.currentThread().interrupt();
//			}
//			myStock.updateQuote();
//			myStock.printStock();
//		}
	}

}
