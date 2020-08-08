This is maven project and the code is built on top of Java 8, the solution is to follow the KISS principle and implemented with a simple command pattern.

Solution details:
1. create a ConfigReader and a CsvReader to read input parameters and CSV file.
2. use a AccountManager to hold all parameters and transactions.
3. each transaction will be translated into a command to execute and update AccountManager.
4. print out result after all transactions processing.

Below 3rd party libs are used only:
1. OpenCSV
2. Junit4

Note: the jar file is uploaded under jar_file, a separate README.txt file in the save folder shows the steps to run the application.