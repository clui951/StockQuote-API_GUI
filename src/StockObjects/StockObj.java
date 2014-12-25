package StockObjects;

import java.security.acl.LastOwnerException;
import java.util.Arrays;
import java.util.Date;

public class StockObj {
	public static int CSV_DATA_LEN = 6;
	protected String name;
	protected String symbol;
	protected String time;
	protected double price;
	protected double openPrice;
	protected double dayHigh;
	protected double dayLow;
	protected double alertLow;
	protected double alertHigh;
	protected String link;
	protected String[] csvData;
	protected boolean valid = false;

	public StockObj(String symbol) {
		this.symbol = symbol;
		// yahoo finance API for sock quotes
		// name, symbol, current trade, day open, high, low
		this.link = "http://download.finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=n0sl1oh0g0&e=.csv";
		this.updateQuote();
		if ( !this.valid ) {
			return;
		}
	}

	public void updateQuote() {
		try {
			String csv = GrabHTML.readHTML(this.link);
			this.csvData = new String[CSV_DATA_LEN];
			
			// Handle cases with comma in stock name
			// split into header/tail and parse individually, combining at end
			String header = csv.substring(0, csv.lastIndexOf("\"")+1); // +1 to include last quotation
			String tail = csv.substring(csv.lastIndexOf("\"") + 2, csv.length()); // +2 to rid of starting comma
			this.csvData[0] = header.substring(0,header.lastIndexOf(",")); 
			this.csvData[1] = header.substring(header.lastIndexOf(",")+1,header.length());	
			String[] numbData = tail.split(",");
			for (int i = 0; i < CSV_DATA_LEN - 2; i++) {
				csvData[i+2] = numbData[i];
			}
			
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
			System.out.println("\n--- DATA IS CURRENTLY INVALID ---");
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
		
		// check if outside of user bounds
		if ((this.alertHigh > 0) && (this.price > this.alertHigh)) {
			System.out.println("Current trade price above user set bound of " + this.alertHigh);
		} else if (this.price < this.alertLow) {
			System.out.println("Current trade price below user set bound of " + this.alertLow);
		}
	}

	public void setAlertLow(double newLow) {
		this.alertLow = newLow;
	}
	
	public double getAlertLow() {
		return this.alertLow;
	}
	
	public void setAlertHigh(double newHigh) {
		this.alertHigh = newHigh;
	}
	
	public double getAlertHigh() {
		return this.alertHigh;
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
        
        public void setValid(boolean bool) {
            this.valid = bool;
        }

}
