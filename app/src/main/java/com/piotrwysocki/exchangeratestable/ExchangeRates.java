package com.piotrwysocki.exchangeratestable;

/**
 * Created by Piotrek on 2017-04-30.
 */

/**
 * An {@link ExchangeRates} object contains information related to a single earthquake.
 */
public class ExchangeRates {

    /**
     * Currency of the exchange rate
     */
    private String mCurrency;

    /**
     * Bid of the exchange rate
     */
    private double mBid;

    /**
     * Ask of the exchange rate
     */
    private double mAsk;

    /**
     * Constructs a new {@link ExchangeRates} object.
     *
     * @param currency is the currency of the current exchange rate
     * @param bid      is the bid of the current exchange rate
     * @param ask      is the ask of the current exchange rate
     */
    public ExchangeRates(String currency, double bid, double ask) {
        mCurrency = currency;
        mBid = bid;
        mAsk = ask;
    }

    /**
     * Returns the currency of the exchange rate.
     */
    public String getCurrency() {
        return mCurrency;
    }

    /**
     * Returns the bid of the exchange rate.
     */
    public double getBid() {
        return mBid;
    }

    /**
     * Returns the ask of the exchange rate.
     */
    public double getAsk() {
        return mAsk;
    }
}
