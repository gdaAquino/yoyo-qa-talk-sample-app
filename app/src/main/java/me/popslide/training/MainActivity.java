package me.popslide.training;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Gian Darren Aquino
 */
public class MainActivity extends Activity {

    private EditText input1;
    private EditText input2;
    private Button add;
    private Button subtract;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** Defines the layout for this activity **/
        setContentView(R.layout.main_activity);

        /** Assign the Views to its Variable so we can access it later **/
        input1 = (EditText) findViewById(R.id.input1);
        input2 = (EditText) findViewById(R.id.input2);
        add = (Button) findViewById(R.id.add);
        subtract = (Button) findViewById(R.id.subtract);
        result = (TextView) findViewById(R.id.result);

        /** Assign the action for the buttons **/
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
                /** Gets the input from the EditText **/
                int n1 = Integer.parseInt(input1.getText().toString());
                int n2 = Integer.parseInt(input2.getText().toString());

                /** Compute **/
                int addResult = Math.add(n1, n2);

                /** Display the result **/
                result.setText(String.valueOf(addResult));
            }
        });

        /** Assign the action for the buttons **/
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
                /** Gets the input from the EditText **/
                int n1 = Integer.parseInt(input1.getText().toString());
                int n2 = Integer.parseInt(input2.getText().toString());

                /** Compute **/
                int subtractResult = Math.subtract(n1, n2);

                /** Display the result **/
                result.setText(String.valueOf(subtractResult));
            }
        });
    }

    public void validateInput() {
        if (TextUtils.isEmpty(input1.getText()) || !TextUtils.isDigitsOnly(input1.getText())) {
            input1.setText("0");
        }
        if (TextUtils.isEmpty(input2.getText()) || !TextUtils.isDigitsOnly(input2.getText())) {
            input2.setText("0");
        }
    }
}
