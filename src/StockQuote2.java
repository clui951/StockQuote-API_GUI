import java.util.Scanner;


public class StockQuote2 {

	static int LOOPS = 3;
	
	public static void main(String[] args) {
		System.out.println("\nHello, API Investor");
		
		while (true) {
			Scanner in = new Scanner(System.in);
			System.out.print("\nEnter a stock symbol ('quit' to quit): ");
			String sym = in.nextLine();
			if ( sym.equals("quit")) {
				break;
			}
			StockObj myStock2 = new StockObj(sym);
			myStock2.printStock();
		}
		
	}

}
