package com.example.android.justjava;
/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity;
    //int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity = Integer.valueOf((((TextView) findViewById(R.id.quantity_text_view)).getText().toString()));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);
    }

    /**
     *
     * @param totalPrice Total price
     * @return Returns Order Summary
     */
    private String createOrderSummary(int totalPrice) {
        String priceMessage = "Name: " + (((EditText) findViewById(R.id.name_field)).getText().toString()) + "\n";
        priceMessage += "Add whipped cream? " + ((CheckBox) findViewById(R.id.whipped_cream_checkbox)).isChecked() + "\n";
        priceMessage += "Add chocolate? " + ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked() + "\n";
        priceMessage += "Quantity: " + quantity + "\n";
        //message += "Total: $" + totalPrice + "\n";
        priceMessage += "Total: " + NumberFormat.getCurrencyInstance().format(totalPrice) + "\n";
        priceMessage += "Thank you!";
        return priceMessage;
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
        //displayPrice(quantity*5);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {

        quantity = quantity - 1;
        if (quantity < 0)
            quantity = 0;
        displayQuantity(quantity);
        //displayPrice(quantity*5);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen
     */

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}