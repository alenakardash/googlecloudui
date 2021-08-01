# googlecloudui
A simple project for automating tests scenarios for [Google Pricing Calculator](https://cloud.google.com/products/calculator) web page.
#### Description of files used in the project:
[AbstractPage:](./src/test/java/AbstractPage.java) An parent PageObject that all classes should inherit to be able to perform WebDriver tests.

[PricingCalculatorPageTest:](./src/test/java/PricingCalculatorPageTest.java) A class that wires all test methods together.

Other class files include the locators, variables and methods to perform test scenarios.

#### Tools:
ChromeDriver version 91.0.4472.101

Java 1.8

TestNg 7.1.0

Selenium 3.141.59

Maven 3.0.0

#### Installation and test run
1. Download and set up JAVA
https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

Select the appropriate JDK version and click Download

to set up JAVA_HOME system environment
#####for MacOS:
Execute the following Terminal commands:
`nano ~/.bash_profile`

`export JAVA_HOME=/usr/libexec/java_home -v <version>`

`source ~/.bash_profile`
#####for Windows:
Right click My Computer and select Properties.
On the Advanced tab, select Environment Variables, and then edit JAVA_HOME to point to where the JDK software is located, for example, C:\Program Files\Java\jdk1.8.0_02

3. Download ChromeDriver from
https://chromedriver.chromium.org/downloads

Set up environment variable for chromedriver
#####for MacOS:
`nano ~/.bash_profile`

`export PATH=$PATH: path/to/executable/chromedriver`

`source ~/.bash_profile`

#####for Windows:
Right click My Computer and select Properties.
On the Advanced tab, select Environment Variables, and then specify PATH where the chromedriver executable is located.
4. Clone the project
`git clone https://github.com/alenakardash/googlecloudui`
 
5. Navigate to googlecloudui directory and run:
`mvn test`