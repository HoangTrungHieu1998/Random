package com.example.random;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText msomin,msomax;
    Button mbtnRandom, maddRange, mReset;
    TextView mResult;
    List<Integer> mArrayListRange;
    int msMin=0;
    int msMax=0;
    String mtextMin = "";
    String mtextMax = "";
    String mKetqua ="";
    Random mRandom;
    int mindex = -1;
    int mvalue = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ
        msomin = findViewById(R.id.somin);
        msomax = findViewById(R.id.somax);
        mbtnRandom = findViewById(R.id.btnRandom);
        maddRange = findViewById(R.id.btnRange);
        mReset = findViewById(R.id.btnReset);
        mResult = findViewById(R.id.ketqua);

        //Khởi tạo mảng và Random
        mArrayListRange = new ArrayList<>();
        mRandom = new Random();

        // Ẩn nút random
        disableView(mbtnRandom);

        mbtnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mArrayListRange.size()>0){
                    mindex = mRandom.nextInt(mArrayListRange.size());
                    mvalue = mArrayListRange.get(mindex);
                    mKetqua += mArrayListRange.size()==1 ? mvalue : mvalue + "-";
                    mResult.setText(mKetqua);
                    mArrayListRange.remove(mindex);
                }else{
                    Toast.makeText(MainActivity.this,
                            "Hết số để random",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        maddRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ edit text
                String textMin = msomin.getText().toString();
                String textMax = msomax.getText().toString();

                //Kiểm tra đã nhập chưa
                if(textMin.isEmpty() || textMax.isEmpty()){
                    Toast.makeText(
                            MainActivity.this,
                            "Chưa nhập đủ thông tin",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                //Chuyển đổi dữ liệu
                int sMin = Integer.parseInt(textMin);
                int sMax = Integer.parseInt(textMax);

                // Kiểm tra số min có bé hơn hoặc bằng số max
                if(sMin>=sMax){
                    sMax = sMin+1;
                }
                msomin.setText(String.valueOf(sMin));
                msomax.setText(String.valueOf(sMax));

                // Xoá các phần tử trong mảng trước đó
                mArrayListRange.clear();

                // Nhập phần tử cho mảng
                for (int i = sMin; i <= sMax; i++) {
                    mArrayListRange.add(i);
                }

                // Ẩn nút AddRange và các edit text
                disableView(maddRange);
                disableView(msomax);
                disableView(msomin);

                // Hiển thị nút Random
                enableView(mbtnRandom);
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xoá edit text
                msomax.setText("");
                msomin.setText("");

                // Hiển thị nút addRange và các edit text
                enableView(maddRange);
                enableView(msomin);
                enableView(msomax);

                // Xoá các phần tử mảng
                mArrayListRange.clear();

                // Ẩn mút Random
                disableView(mbtnRandom);

                // Xoá text view
                mResult.setText("");
            }
        });
    }
    private void disableView(View view){
        view.setEnabled(false);
    }
    private void enableView(View view){
        view.setEnabled(true);
    }
}