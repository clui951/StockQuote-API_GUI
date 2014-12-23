import java.util.Arrays;
import java.util.Date;

public class StockObj {
	protected String name;
	protected String symbol;
	protected String time;
	protected double price;
	protected double openPrice;
	protected double dayHigh;
	protected double dayLow;
	protected String link;
	protected String[] csvData;
	protected boolean valid = false;

	public StockObj(String symbol) {
		this.symbol = symbol;
		// name, symbol, current trade, day open, high, low
		this.link = "http://download.finance.yahoo.com/d/quotes.csv?s=%40%5EDJI,GOOG&f=n0sl1oh0g0&e=.csv";
		this.updateQuote();
		if ( !this.valid ) {
			return;
		}
	}

	public void updateQuote() {
		try {
			String csv = GrabHTML.readHTML(this.link);
			this.csvData = csv.split(",");
			this.name = csvData[0];
			this.symbol = csvData[1];
			this.price = Double.parseDouble(csvData[2]);
			this.openPrice = Double.parseDouble(csvData[3]);
			this.dayHigh = Double.parseDouble(csvData[4]);
			this.dayLow = Double.parseDouble(csvData[5]);
			this.time = (new Date()).toString();
			this.valid = true;
		} catch (Exception e1) {
//			e1.printStackTrace();
			this.valid = false;
		}
	}
	
	public void printStock() {
		if ( !this.valid ) {
			System.out.println("--- DATA IS CURRENTLY INVALID ---");
		}
		
		// set up name underline
		int headLen = (this.name + " - " + this.symbol).length();
		char[] underline = new char[headLen];
		char underlineSymbol = ("-").charAt(0);				
		Arrays.fill(underline, underlineSymbol);
		
		System.out.println("\n" + this.name + " - " + this.symbol);
		System.out.println(underline);
		System.out.println(this.time);
		System.out.printf("%-10s %s", "Current:", "$ " + this.price + "\n");
		System.out.printf("%-10s %s", "Open:", "$ " + this.openPrice + "\n");
		System.out.printf("%-10s %s", "High/Low:", "$ " + this.dayHigh +"/" + this.dayLow + "\n\n");
	}

	public String getSymbol() {
		return this.symbol;
	}

	public String getName() {
		return this.name;
	}

	public String getTime() {
		return this.time;
	}

	public double getPrice() {
		return this.price;
	}

	public double getOpenPrice() {
		return this.openPrice;
	}

	public double getDayHigh() {
		return this.dayHigh;
	}

	public double getDayLow() {
		return this.dayLow;
	}
	
	public String[] getCsvData() {
		return this.csvData;
	}

	public boolean isValid() {
		return this.valid;
	}

}
