package com.example.delfinv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import static android.Manifest.permission.INTERNET;

public class SplashScreen extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_INTERNET_USE = 1000 ;
    private ProgressBar splashProgress;
    private int SPLASH_TIME = 1000;
    private Animation anim;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        InitComponents();
        CheckPermissions();
    }

    //Check if the permits have been granted

    private void CheckPermissions(){

        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            InitApp();
        }else{

            if (ContextCompat.checkSelfPermission(SplashScreen.this,
                    INTERNET)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(SplashScreen.this,
                        INTERNET)) {

                    loadRecommendation();

                } else {

                    ActivityCompat.requestPermissions(SplashScreen.this,
                            new String[]{INTERNET},
                            MY_PERMISSIONS_REQUEST_INTERNET_USE);

                }
            }else{
                InitApp();
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_INTERNET_USE){
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                InitApp();
            }else{
                requestManualPermit();
            }
        }
    }

    // Apertura de asignacion manual de permisos
    private void requestManualPermit() {
        final CharSequence[] options ={getString(R.string.yes),getString(R.string.no)};
        final AlertDialog.Builder alert = new AlertDialog.Builder(SplashScreen.this);
        alert.setTitle(getString(R.string.configQuesion));
        alert.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (options[which].equals(getString(R.string.yes))){
                    Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri u  = Uri.fromParts("Package", getPackageName(),null);
                    i.setData(u);
                    startActivity(i);
                }else{
                    Toast.makeText(SplashScreen.this,getString(R.string.nonPermission),Toast.LENGTH_SHORT);
                    dialog.dismiss();
                }
            }
        });
    }

    // Asignacion automatica de permisos
    private void loadRecommendation() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(SplashScreen.this);
        dialog.setTitle(getString(R.string.titlleDialogPermission));
        dialog.setMessage(getString(R.string.configQuesion));

        dialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions( new String []{ INTERNET},MY_PERMISSIONS_REQUEST_INTERNET_USE);
                }
            }
        });
        dialog.show();
    }

    // Inico de hilos de ejecucion
    private void InitApp(){

        playProgress();
        setAnim(R.anim.fade_in);
        imageView.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mySuperIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(mySuperIntent);
                finish();
            }
        }, SPLASH_TIME);

    }

    //Inicio de progressBar
    private void playProgress() {
        ObjectAnimator.ofInt(splashProgress, "progress", 100)
                .setDuration(SPLASH_TIME)
                .start();
    }

    // Asignacion de animacion a logo de entrada
    private void setAnim(int id){
        anim = AnimationUtils.loadAnimation(getApplicationContext(),id);
    }

    //Crea instancias de elementos de interfaz
    private void InitComponents(){
        imageView=(ImageView)findViewById(R.id.logoSplash);
        splashProgress = findViewById(R.id.splashProgress);
    }
}