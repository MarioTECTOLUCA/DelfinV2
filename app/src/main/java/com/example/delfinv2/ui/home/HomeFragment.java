package com.example.delfinv2.ui.home;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.delfinv2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {

    private TextView countDownTimerText;
    private CountDownTimer countDownTimer;
    private long timeless;
    private Date actualDate, targetDate;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        InitComponents();
        startCountDown();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void InitComponents(){
        countDownTimerText = root.findViewById(R.id.homeTextCountDown);

    }

    private void startCountDown(){
        String dtStart = "01/11/2021";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try{
            targetDate = format.parse(dtStart);
        }catch (Exception e){

        }
        actualDate = new Date();
        timeless = targetDate.getTime() - actualDate.getTime();
        countDownTimer = new CountDownTimer(timeless, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeless = targetDate.getTime() - actualDate.getTime();

                long seg = (int) (timeless / 1000) % 60 ;;
                int min = (int) ((timeless / (1000*60)) % 60);
                int hr = (int) ((timeless / (1000*60*60)) % 24);
                int dias = (int) (timeless / (1000*60*60*24));
                String time = dias + " : " + hr % 24 + " : " + min % 60 + " : " + seg % 60;
                updateTime(time);
            }

            @Override
            public void onFinish() {
                countDownTimerText.setText(R.string.countdown_finish);
            }
        }.start();
    }

    private void updateTime(String time){
        countDownTimerText.setText(time);
    }
}