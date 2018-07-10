package com.example.administrator.summarize.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.administrator.summarize.R;
import com.example.administrator.summarize.utils.CalculateUtils;

public class CalculateActivity extends Activity {
    private TextView answer_1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        answer_1 = (TextView)findViewById(R.id.answer_1);
        answer_1.setText(CalculateUtils.calculate());
    }
}
