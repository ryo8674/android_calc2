package com.example.peter.calculator2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private int num;
    private int result;
    private int recentKey;
    private boolean isOperationKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);

        findViewById(R.id.button_0).setOnClickListener(numberListener);
        findViewById(R.id.button_1).setOnClickListener(numberListener);
        findViewById(R.id.button_2).setOnClickListener(numberListener);
        findViewById(R.id.button_3).setOnClickListener(numberListener);
        findViewById(R.id.button_4).setOnClickListener(numberListener);
        findViewById(R.id.button_5).setOnClickListener(numberListener);
        findViewById(R.id.button_6).setOnClickListener(numberListener);
        findViewById(R.id.button_7).setOnClickListener(numberListener);
        findViewById(R.id.button_8).setOnClickListener(numberListener);
        findViewById(R.id.button_9).setOnClickListener(numberListener);

        findViewById(R.id.button_add).setOnClickListener(operationListener);
        findViewById(R.id.button_sub).setOnClickListener(operationListener);
        findViewById(R.id.button_div).setOnClickListener(operationListener);
        findViewById(R.id.button_equal).setOnClickListener(operationListener);
    }

    // 数値が押された時の処理
    private View.OnClickListener numberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            button = (Button) view;

            // 入力
            if (isOperationKey) {
                num = 0;
                result = 0;
                textView.setText("");
            }
            num = num * 10 + Integer.parseInt(button.getText().toString());

            textView.append(button.getText());
            isOperationKey = false;
        }
    };

    // 演算子が押された時の処理
    private View.OnClickListener operationListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            button = (Button) view;
            isOperationKey = false;

            if (button.getId() == R.id.button_equal) {
                result = calc(recentKey, result, num);
                textView.setText(String.valueOf(result));
                isOperationKey = true;
            } else if (result == 0) {
                result = num;
                textView.append(button.getText());
            } else {
                result = calc(button.getId(), result, num);
                textView.append(button.getText());
            }

            num = 0;
            recentKey = button.getId();
        }
    };

    // 計算処理
    private int calc(int operationKey, int value1, int value2) {
        switch (operationKey) {
            case R.id.button_add:
                return value1 + value2;
            case R.id.button_sub:
                return value1 - value2;
            case R.id.button_div:
                if (value1 == 0 || value2 == 0) {
                    return 0;
                } else {
                    return value1 / value2;
                }
            default:
                return value1;
        }
    }

    void clearResult(View view) {
        recentKey = R.id.button_equal;
        result = 0;
        isOperationKey = false;
        num = 0;
        textView.setText("");
    }
}