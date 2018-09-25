package com.example.cartermccall.beautifulbulldog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BulldogListActivity extends AppCompatActivity {

    private TextView text_View2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog_list);

        text_View2 = (TextView) findViewById(R.id.textView2);

        String email = getIntent().getStringExtra("email");
        text_View2.setText(email);
    }
}
