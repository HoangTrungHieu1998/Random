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

    EditText mEdtsomin,mEdtsomax;
    Button mBtnRandom,mBtnReset,mBtnAddRange;
    TextView mTvResult;
    String mKetqua ="";
    List<Integer> mArrayListRange;
    String mtextsomin = "";
    String mtextsomax = "";
    int msMin=0;
    int msMax=0;
    Random mRandom;
    int mIndexRandom = -1;
    int mValue=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdtsomax = findViewById(R.id.somax);
        mEdtsomin = findViewById(R.id.somin);
        mBtnRandom = findViewById(R.id.btnRandom);
        mTvResult = findViewById(R.id.ketqua);
        mBtnReset = findViewById(R.id.btnReset);
        mBtnAddRange = findViewById(R.id.btnRange);


        // leak memory: rò rỉ vùng nhớ
        mArrayListRange = new ArrayList<>();
        mRandom = new Random();

        disableView(mBtnRandom);

        mBtnAddRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // lấy thông tin từ edit text
                String textsomin = mEdtsomin.getText().toString();
                String textsomax = mEdtsomax.getText().toString();
                // Kiểm tra chuỗi rỗng
                if(textsomin.isEmpty() || textsomax.isEmpty()){
                    //xuất thông báo
                    Toast.makeText(
                            MainActivity.this,
                            "Bạn chưa nhập đủ thông tin",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                //đổi kiểu dữ liệu string -> integer
                int sMin = Integer.parseInt(textsomin);
                int sMax = Integer.parseInt(textsomax);

                // Kiểm tra số min không được lớn hơn hoặc bằng số max
                if(sMin>=sMax){
                    sMax = sMin+1;
                }
                mEdtsomin.setText(String.valueOf(sMin));
                mEdtsomax.setText(String.valueOf(sMax));
                mArrayListRange.clear();

                // Thêm phần tử vào mảng
                 for (int i = sMin; i <= sMax ; i++) {
                    mArrayListRange.add(i);
                }
                 // Ẩn nút AddRange và Edit Text
                 disableView(mBtnAddRange);
                 disableView(mEdtsomin);
                 disableView(mEdtsomax);
                 enableView(mBtnRandom);
            }
        });

        mBtnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mArrayListRange.size()>0){
                    mIndexRandom = mRandom.nextInt(mArrayListRange.size());
                    mValue = mArrayListRange.get(mIndexRandom);
                    mKetqua += mArrayListRange.size() !=1 ? mValue + "-" : mValue;
                    mTvResult.setText(mKetqua);
                    mArrayListRange.remove(mIndexRandom);
                }else{
                    Toast.makeText(
                            MainActivity.this,
                            "Hết giá trị random",
                            Toast.LENGTH_SHORT
                    ).show();
                }


                // Xử lý Random
                //Random random = new Random();
                //int value = random.nextInt(sMax-sMin+1)+sMin;
                //mKetqua += value + " - ";
                //mTvResult.setText(mKetqua);
            }
        });
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1: clear mArrayListRange, set edit text thành chuỗi rỗng
                mArrayListRange.clear();
                mEdtsomin.setText("");
                mEdtsomax.setText("");
                // 2: Bật các button ẩn
                enableView(mEdtsomin);
                enableView(mBtnAddRange);
                disableView(mBtnRandom);
                enableView(mEdtsomax);
                //visibleView(mEdtsomin);
                //visibleView(mEdtsomax);
                // 3: Clear các số đã random
                mTvResult.setText("");
            }
        });
    }
    // Hàm hiển thị
    private void enableView(View view){
        view.setEnabled(true);
    }
    // Hàm ẩn
    private void disableView(View view){
        view.setEnabled(false);
    }
    private void visibleView(View view){
        view.setVisibility(View.VISIBLE);
    }
    private void invisibleView(View view){
        view.setVisibility(View.INVISIBLE);
    }
}