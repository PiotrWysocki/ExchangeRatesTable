package com.piotrwysocki.exchangeratestable;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<ExchangeRates>> {

    /**
     * URL for exchange rates data from the NBP dataset
     */
    private static final String NBP_REQUEST_URL =
            "http://api.nbp.pl/api/exchangerates/tables/C/?format=json";

    /**
     * Constant value for the exchange rates loader ID.
     */
    private static final int EXCHANGE_RATES_LOADER_ID = 1;

    /**
     * Adapter for the list of exchange rates
     */
    private ExchangeRatesAdapter mAdapter;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        ListView exchangeRatesListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        exchangeRatesListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of exchange rates as input
        mAdapter = new ExchangeRatesAdapter(this, new ArrayList<ExchangeRates>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        exchangeRatesListView.setAdapter(mAdapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager conManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(EXCHANGE_RATES_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<ExchangeRates>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new ExchangeRatesLoader(this, NBP_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<ExchangeRates>> loader, List<ExchangeRates> exchangeRates) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No exchange rates found."
        mEmptyStateTextView.setText(R.string.no_exchange_rates);

        // Clear the adapter of previous exchange rates data
        mAdapter.clear();

        // If there is a valid list of {@link ExchangeRates}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (exchangeRates != null && !exchangeRates.isEmpty()) {
            mAdapter.addAll(exchangeRates);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<ExchangeRates>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

}
