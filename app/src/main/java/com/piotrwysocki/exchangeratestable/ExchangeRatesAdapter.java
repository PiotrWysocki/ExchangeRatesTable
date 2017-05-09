package com.piotrwysocki.exchangeratestable;

/**
 * Created by Piotrek on 2017-04-30.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * An {@link ExchangeRatesAdapter} knows how to create a list item layout for each exchange rates
 * in the data source (a list of {@link ExchangeRates} objects).
 * <p>
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class ExchangeRatesAdapter extends ArrayAdapter<ExchangeRates> {

    /**
     * Constructs a new {@link ExchangeRatesAdapter}.
     *
     * @param context       of the app
     * @param exchangeRates is the list of exchange rates, which is the data source of the adapter
     */
    public ExchangeRatesAdapter(Context context, List<ExchangeRates> exchangeRates) {
        super(context, 0, exchangeRates);
    }

    /**
     * Returns a list item view that displays information about the exchange rates at the given position
     * in the list of exchange rates.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.exchange_rate_list_item, parent, false);
        }

        // Find the exchange rate at the given position in the list of exchange rates
        ExchangeRates currentExchangeRates = getItem(position);

        // Find the TextView with view ID currency
        TextView currencyView = (TextView) listItemView.findViewById(R.id.currency);
        // Display the currency of the current exchange rate in that TextView
        currencyView.setText(currentExchangeRates.getCurrency());

        // Find the TextView with view ID bid label
        TextView bidLabelView = (TextView) listItemView.findViewById(R.id.bid_label);
        // Display the bid label of the exchange rate in that TextView
        bidLabelView.setText(R.string.bid_label);

        // Find the TextView with view ID bid
        TextView bidView = (TextView) listItemView.findViewById(R.id.bid);
        // string representation of the double value
        String bidText = String.valueOf(currentExchangeRates.getBid());
        // Display the bid of the current exchange rate in that TextView
        bidView.setText(bidText);

        // Find the TextView with view ID ask label
        TextView askLabelView = (TextView) listItemView.findViewById(R.id.ask_label);
        // Display the ask label of the exchange rate in that TextView
        askLabelView.setText(R.string.ask_label);

        // Find the TextView with view ID ask
        TextView askView = (TextView) listItemView.findViewById(R.id.ask);
        // string representation of the double value
        String askText = String.valueOf(currentExchangeRates.getAsk());
        // Display the ask of the current exchange rate in that TextView
        askView.setText(askText);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}
