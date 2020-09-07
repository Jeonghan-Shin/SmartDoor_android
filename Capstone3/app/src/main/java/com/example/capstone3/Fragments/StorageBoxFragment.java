package com.example.capstone3.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.capstone3.MyClientTask;
import com.example.capstone3.R;

public class StorageBoxFragment extends Fragment {
    private View rootView;

    private String ipAddress = "";
    private int port;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(getArguments() != null) {
            ipAddress = getArguments().getString("ipAddress");
            port = getArguments().getInt("port");
        }

        rootView = inflater.inflate(R.layout.fragment_box, container, false);

        final ImageButton imageButton = (ImageButton)rootView.findViewById(R.id.btn_box);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MyClientTask myClientTask = new MyClientTask(ipAddress, port, "storage");
                myClientTask.execute();
                Toast.makeText(getActivity(), "보관함이 열렸습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
