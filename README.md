Retrieve stock quotes based on symbol
====================================

Uses Yahoo Finance API, JFC/Swing, and Java Threading
----------------------

**Jar File can be downloaded and executed from the 'dist' folder.**

Main window of program takes in a stock symbol, highs and lows to be alerted if price goes beyond (default 0 will not activate alerts), and number of seconds between each refresh of the quote (default 0 will not refresh).

Upon pressing okay, the stock name, last update time, current price, day opening price, and day's high and low is displayed. It will update according to the inputted number of seconds. If the stock price ever goes beyond the provided bounds, the price will turn red and beep to indicate that attention is needed.

Multiple stock quote pages can be opened at once.
