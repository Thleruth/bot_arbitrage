# Cryptocurrency arbitrage and detection bot.

A bot designed to detect arbitrage opportunities for cryptocurrencies by checking the price on various exchanges for the purpose of trading and information gathering and logging.

**Warning:**  Any liabilities or damages
induced by the use of this tool is the sole responsibility of the user of this tool and not the developers.

## Description

This is a bot that utilizes the [XChange Java library](http://knowm.org/open-source/xchange/) to interact with the apis
of leading cryptocurrency exchanges. The bot has two primary functions, arbitrage and detection. For the the detection function,
the user must enter the exchanges and currency pairs they would like to check as well as an action selection. The detection action selections
are print and log. The bot will then either print the currency pair and exchanges with the greatest difference in price to the console or log
the highest ask price, lowest ask price and price difference for each request to a database repeatedly at a desired time interval. For the arbitrage
function there are three actions,print, email and trading. For each of the actions the user must enter a currency pair a list of exchanges a desired
arbitrage margin and an action. The print action will print the best arbitrage opportunity found for the given pair and exchanges to the console.
The email action will additionally require an email address that is verified by amazon aws in the call. The email address will send an arbitrage update
with the best arbitrage opportunity to the given email address. For the final arbitrage action (trading) the user must give a trade value base (a trade value
in the base currency selected) along with the call. The bot will then trade on the exchange with the lowest ask and highest bid to capitalize on the arbitrage,
and insert information related to the trade into a database.


## Exchange List and API Documentation

Exchanges:


* [GDAX](https://docs.gdax.com/)

* [Kraken](https://www.kraken.com/help/api)

* [Poloniex](https://poloniex.com/support/api/) no fiat currencies

* [Gemini](https://docs.gemini.com/rest-api/)

* [Bittrex](https://bittrex.com/home/api)

* [Bitstamp](https://www.bitstamp.net/api/)

* [Bitfinex](https://docs.bitfinex.com/docs) non US residents only

* [Binance](https://support.binance.com/hc/en-us/articles/115003235691-Binance-API-Trading-Rules)


## Exchange Notes:

* For best use, make sure that the currency pair or pairs you are using are supported by the selected exchanges.

* For arbitrage trade action, you will need to create an account on each exchange you would like to use and obtain the api key, api secret key
and any other needed api specifications.

* For each currency pair the first currency is the base currency and the second is the counter currency. For example for the currency pair ETH_BTC,
ETH (ethereum) is the base currency and BTC (bitcoin) is the counter currency.

* You must set the tradeValueBase or amount of base currency you would like to trade in the arbitrage trade action method. Different exchanges have different
minimums on the amount of each currency they will allow you to trade. Find the limits of each exchange before you trade.

* To be able to complete an arbitrage trade you must make sure that you have at least enough base currency on the wallets of each exchange as the tradeValueBase set in the method.
additionally you will need enough counter currency to purchase the amount of baseCurrency set in the method. For example for the CurrencyPair ETH_BTC, if you set the
tradeValue base as 0.02 in the method, it means you wish to trade 0.02 ETH, you must make sure that you have at least 0.02 ETH on each exchange you are selecting and that
you have enough BTC to purchase 0.02 ETH on each exchange as well.



## Getting Started

* Requires a [Java 8 JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) to be
installed on the machine you are going to use to run the program.

* For arbitrage email action you must have an active account on amazon [aws](https://docs.aws.amazon.com/ses/latest/DeveloperGuide/smtp-credentials.html) and the required credentials.

* Make sure you set region=us-east-1 as your region.

* You must have a valid email address verified by aws that you can hardcode into the From variable in the [Email class](https://github.com/Thleruth/bot_arbitrage/tree/master/src/main/java/co/codingnomads/bot/arbitrage/model/email)
  in replace of cryptoarbitragebot25@gmail.com.

* Once you have one email address verified you can verify [additional email addresses](https://console.aws.amazon.com/ses/home?region=us-east-1#verified-senders-email) and set them in the [controller](https://github.com/Thleruth/bot_arbitrage/blob/master/src/main/java/co/codingnomads/bot/arbitrage/Controller.java)
  in arbitrageEmailAction method in replace of "your-email-address" to have an arbitrage update sent to that address.

* For arbitrage trade action, arbitrage email action and detection log action, requires [MySQL](https://dev.mysql.com/downloads/mysql/) version 5.7 or higher to be installed on the machine and running.


Required:

* [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](http://maven.apache.org/download.cgi)
* [MySQL](https://dev.mysql.com/downloads/mysql/)

Clone the project to your desired location

```
$ git clone https://github.com/Thleruth/bot_arbitrage

```


Set up aws credentials:

obtain [aws credentials](https://docs.aws.amazon.com/ses/latest/DeveloperGuide/smtp-credentials.html)

copy credentials

```
$ cd /~
$ mkdir .aws
$ cd .aws
$ touch credentials
$ vim credentials
```

paste credentials and save


Set up database:

```
$ mysql -u root -p
 > mysql CREATE DATABASE bot.arbitrage;
 > mysql USE bot.arbitrage;
 > mysql SOURCE /../bot_arbitrage/src/main/mysql/bot.arbitrage.sql;
```

configure and run:

```
$ cd bot_arbitrage
$ cd /bot_arbitrage/src/main/java/co/codingnomads/bot/arbitrage/
$ open Controller.java

```

### choose 1 of the following actions to run, (default is set to arbitrage print action). You may uncomment the examples of the actions to use them.

* Arbitrage Print Action - prints the lowest ask, highest bid and the exchanges for each to console.

* Arbitrage Email Action - emails arbitrage update of lowest ask, highest bid and exchanges. Enters email info into database.

* Arbitrage Trade Action - trades counter currency for base currency on the exchanges with the lowest ask and highest bid. Enters trade history into database.

* Detection print Action - Checks multiple exchanges for multiple currency pairs and prints the exchanges with the greatest difference to the console.

* Detection log Action - logs the exchanges and currency pairs with the greatest differences to the database at a given time interval.

### enter desired parameters see ACTION EXAMPLES below for more information

once you are all set up

```
$ cd bot_arbitrage
$ mvn clean package
$ mvn spring-boot:run

```

### Maven
Add the following dependencies in your [`pom.xml`](https://github.com/Thleruth/bot_arbitrage/blob/master/pom.xml)

#### Dependencies
```xml

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.1</version>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.amazonaws</groupId>
            <artifactId>aws-java-sdk</artifactId>
            <version>1.11.106</version>
        </dependency>
        <dependency>
            <groupId>org.knowm.xchange</groupId>
            <artifactId>xchange-core</artifactId>
            <version>4.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.knowm.xchange</groupId>
            <artifactId>xchange-gdax</artifactId>
            <version>4.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.knowm.xchange</groupId>
            <artifactId>xchange-kraken</artifactId>
            <version>4.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.knowm.xchange</groupId>
            <artifactId>xchange-binance</artifactId>
            <version>4.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.knowm.xchange</groupId>
            <artifactId>xchange-bitstamp</artifactId>
            <version>4.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.knowm.xchange</groupId>
            <artifactId>xchange-poloniex</artifactId>
            <version>4.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.knowm.xchange</groupId>
            <artifactId>xchange-bitfinex</artifactId>
            <version>4.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.knowm.xchange</groupId>
            <artifactId>xchange-bittrex</artifactId>
            <version>4.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.knowm.xchange</groupId>
            <artifactId>xchange-gemini</artifactId>
            <version>4.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.5</version>
        </dependency>
```

#### Plugin
``` xml
    <plugin>
         <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-maven-plugin</artifactId>
     </plugin>
```

# Action Examples

WIP

## Contributors

* [Thomas Leruth](https://github.com/Thleruth)
* [Kevin Neag](https://github.com/neagkv)


## Acknowledgements
*


