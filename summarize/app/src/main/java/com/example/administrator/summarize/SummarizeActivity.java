package com.example.administrator.summarize;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

public class SummarizeActivity extends Activity implements View.OnClickListener{

    private TextView light_word;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        light_word = (TextView)findViewById(R.id.light_word);
        light_word.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.light_word:
                Intent intent = new Intent(SummarizeActivity.this,LightWordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
