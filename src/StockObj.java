public class StockObj {
	protected String name;
	protected String symbol;
	protected long time;
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
			this.time = 0;
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
		System.out.println("\n" + this.name + " - " + this.symbol);
		System.out.println(this.time);
		System.out.println("Current: $" + this.price);
		System.out.println("Open: $" + this.openPrice);
	}




	public String getSymbol() {
		return this.symbol;
	}

	public String getName() {
		return this.name;
	}

	public long getTime() {
		return this.time;
	}

	public double getPrice() {
		return this.price;
	}

	public String[] getCsvData() {
		return this.csvData;
	}

	public boolean isValid() {
		return this.valid;
	}

}
