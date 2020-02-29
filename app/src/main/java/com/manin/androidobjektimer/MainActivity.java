package com.manin.androidobjektimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    //Membuat Variable ShareAction Provider
    private ShareActionProvider shareActionProvider;

    AppCompatTextView textView=null;
    private Handler handler=new Handler();
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //panggil timer
        handler.postDelayed(runnable, 1000);
    }

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {

            textView=findViewById(R.id.id_tv_timer);
            i+=1;
           // for (i=0; i<50;i++)
             textView.setText(Integer.toString(i));
            handler.postDelayed(this,1000);

        }
    };



    //menu bar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Menginisialisasi MenuBar yang akan ditampilkan pada ActionBar/Toolbar
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bar, menu);
        //return true;

        //MenuItem yang akan dijadikan ShareActionProvider
        MenuItem item = menu.findItem(R.id.share);

        //Ambil dan Simpan ShareActionProvider
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
//        setShare();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.rate:
                //Menangani kejadian saat Tombol Rate Diklik
                try{
                    //Jika Terdapat Google PlayStore pada Perangkat Android
                    //Maka akan langsung terhubung dengan PlayStore Tersebut
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +
                            "indonesia.cianjur.developer.net.mobile_cbt_smk&hl=in")));
                }catch (ActivityNotFoundException ex){
                    //Jika tidak, maka akan terhubung dengan Browser
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com" +
                            "/store/apps/details?id=indonesia.cianjur.developer.net.mobile_cbt_smk&hl=in")));
                }
                break;

            case R.id.share:
                setShare();
                break;
        }
        return true;

    }


    private void setShare(){
        ApplicationInfo appInfo = getApplicationContext().getApplicationInfo();
        String apkPath = appInfo.sourceDir;
        Intent Share = new Intent();
        Share.setAction(Intent.ACTION_SEND);
        Share.setType("application/vnd.android.package-archive");
        Share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
    //    shareActionProvider.setShareIntent(Share);
    }

}
