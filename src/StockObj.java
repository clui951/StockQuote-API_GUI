public class StockObj {
	protected String symbol;
	protected String name;
	protected String time;
	protected double price;
	protected String link;
	protected String html;
	protected boolean valid = false;

	public StockObj(String symbol) {
		this.symbol = symbol;
		this.link = "http://finance.yahoo.com/q?s=" + symbol;
		this.updateQuote();
		if ( !this.valid ) {
			return;
		}
		// Set name of stock
		int titleTag = this.html.indexOf("<title>", 0);
		int nameFrom = this.html.indexOf("Summary for ", titleTag) + 12; //12 offset, start from after string
		int nameTo = this.html.indexOf("- Yahoo! Finance", nameFrom);
		String newName = this.html.substring(nameFrom, nameTo);
		this.name = newName;
	}

	public void updateQuote() {
		try {
			this.html = GrabHTML.readHTML(this.link);
			this.updatePrice();
			this.updateTime();
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
		System.out.println("$ " + this.price + "\n");
	}

	protected void updatePrice() {
		int startTag = this.html.indexOf("yfs_l84", 0);
		int priceFrom = this.html.indexOf( "\">", startTag) + 2; // 2 offset, start from after >
		int priceTo = this.html.indexOf( "</span>", priceFrom);
		String newPrice = this.html.substring( priceFrom, priceTo);
		this.price = Double.parseDouble(newPrice);
	}

	protected void updateTime() {
		int p = html.indexOf("<span id=\"yfs_market_time\">", 0);
		int dateFrom = html.indexOf(">", p) + 1; // 1 offset, start after >
		int dateTo = html.indexOf("-", dateFrom); // end on hyphen, before market close time
		this.time = html.substring(dateFrom, dateTo);
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

	public String getHtml() {
		return this.html;
	}

	public boolean isValid() {
		return this.valid;
	}

}
