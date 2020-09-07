package com.example.capstone3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.capstone3.Fragments.CCTVFragment;
import com.example.capstone3.Fragments.DoorFragment;
import com.example.capstone3.Fragments.SetFragment;
import com.example.capstone3.Fragments.StorageBoxFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private String ipAddress = "";
    private int port = 8888;

    private boolean camOn = false;

    private Fragment[] fragments;
//    private WebView webView;
//    private WebSettings webSettings;
//    private Button btn_Stream;
//    private ImageButton btn_door;
//    private ImageButton btn_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        webView = (WebView) findViewById(R.id.webView);
//        btn_Stream = (Button) findViewById(R.id.btnStream);
//        btn_door = (ImageButton) findViewById(R.id.btn_door);
//        btn_box = (ImageButton) findViewById(R.id.btn_box);

//        webView.setWebViewClient(new WebViewClient());
//        webView.setBackgroundColor(255);

//        webSettings = webView.getSettings();
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setJavaScriptEnabled(true);

        Intent intent = getIntent();
        ipAddress = intent.getStringExtra("ipAddress");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragments = new Fragment[]{
                new DoorFragment(), new StorageBoxFragment(), new CCTVFragment(), new SetFragment()
        };

        // ---데이터(ip, port) 전달---
        Bundle bundle = new Bundle();
        bundle.putString("ipAddress", ipAddress);
        bundle.putInt("port", port);
        for (int i = 0; i < fragments.length; i++)
            fragments[i].setArguments(bundle);
        // ------

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragments[0]).commit();


        // ----- 카메라 버튼 -----
//        btn_Stream.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (camOn == false) {
//                    webView.loadUrl("http://" + ipAddress + ":8080/stream/video.mjpeg");
//                    btn_Stream.setText("카메라 종료");
//                    camOn = true;
//                } else {
//                    webView.loadUrl("");
//                    btn_Stream.setText("카메라 확인");
//                    camOn = false;
//                }
//            }
//        });

        // ----- 문 버튼 -----
//        btn_door.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MyClientTask myClientTask = new MyClientTask(ipAddress, port, "문 열기");
//                myClientTask.execute();
//            }
//        });

        // ----- 보관함 버튼 -----
//        btn_box.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MyClientTask myClientTask = new MyClientTask(ipAddress, port, "보관함 열기");
//                myClientTask.execute();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = fragments[0];


            switch (item.getItemId()) {
                case R.id.navigation_door:
                    fragment = fragments[0];
                    break;
                case R.id.navigation_box:
                    fragment = fragments[1];
                    break;
                case R.id.navigation_cctv:
                    fragment = fragments[2];
                    break;
                case R.id.navigation_set:
                    fragment = fragments[3];
                    break;
                default:
                    return false;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();

            return true;
        }
    };

}
