package com.example.capstone3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = (EditText) findViewById(R.id.password);
    }

    // arrow 버튼 눌렀을 때 Log_in 액티비티로 이동
    public void onClick_log_in(View v) {
        Intent intent_log_in = new Intent(getApplicationContext(), MainActivity.class);
        String ip = editText.getText().toString();
        intent_log_in.putExtra("ipAddress", ip);
        startActivity(intent_log_in);
    }
}
