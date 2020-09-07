package com.example.capstone3.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.capstone3.R;

public class CCTVFragment extends Fragment {
    private View rootView;

    private WebView webView;
    private WebSettings webSettings;
    private Button btn_Stream;

    private TextView txt;

    private boolean camOn = false;
    private String ipAddress = "";
    private int port;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_cctv, container, false);

        webView = (WebView) rootView.findViewById(R.id.webView);
        btn_Stream = (Button) rootView.findViewById(R.id.btnStream);

        txt = (TextView) rootView.findViewById(R.id.txt);

        webView.setWebViewClient(new WebViewClient());
        webView.setBackgroundColor(255);

        webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);

        if(getArguments() != null) {
            ipAddress = getArguments().getString("ipAddress");
            port = getArguments().getInt("port");
        }

        btn_Stream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camOn == false) {
                    webView.loadUrl("http://" + ipAddress + ":8080/stream/video.mjpeg");
                    btn_Stream.setText("카메라 종료");

//                    txt.setText("ip: "+ ipAddress + ", port: " + port);

                    camOn = true;
                } else {
                    webView.loadUrl("");
                    btn_Stream.setText("카메라 확인");
                    camOn = false;
                }
            }
        });

        return rootView;
    }
}
