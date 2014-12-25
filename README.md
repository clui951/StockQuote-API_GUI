Retrieve stock quotes based on symbol
====================================

Uses Yahoo Finance API, JFC/Swing, and Java Threading
----------------------

**Jar File can be downloaded and executed from the 'dist' folder.**

---------

**Program Implementation**
* GrabHTML.java uses Yahoo Finance API to download requested stock information given a stock symbol and options from a property list.
* StockObj.java takes in specific stock information from the API and creates a stock object.
* StockQuoteMain.java is the main function of our Swing GUI, and creates an interface that requests user input.
* GUIThread.java proceeds to create a new java thread to execute a different stock quote page independently of other pages.
* StockQuotePage.java is a Swing GUI jFrame that is unique to each stock created. It displays the stock information, alerts users if requested, and refreshes at certain intervals.

--------

**Program Description**
Main window of program takes in a stock symbol, highs and lows to be alerted if price goes beyond (default 0 will not activate alerts), and number of seconds between each refresh of the quote (default 0 will not refresh).

Upon pressing okay, the stock name, last update time, current price, day opening price, and day's high and low is displayed. It will update according to the inputted number of seconds. If the stock price ever goes beyond the provided bounds, the price will turn red and beep to indicate that attention is needed.

Multiple stock quote pages can be opened at once.
