package com.piotrwysocki.exchangeratestable;

/**
 * Created by Piotrek on 2017-04-30.
 */

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Loads a list of exchange rates by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class ExchangeRatesLoader extends AsyncTaskLoader<List<ExchangeRates>> {

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link ExchangeRatesLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public ExchangeRatesLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<ExchangeRates> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of exchange rates.
        List<ExchangeRates> exchangeRates = QueryUtils.fetchExchangeRatesData(mUrl);
        return exchangeRates;
    }
}
