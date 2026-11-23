package com.example.assignment1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    public interface OnFragment2InteractionListener {
        void onContinueClicked(String userName);
    }

    private OnFragment2InteractionListener listener;
    private EditText etName;
    private EditText etExtraInfo;
    private Button btnContinue;

    public Fragment2() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment2InteractionListener) {
            listener = (OnFragment2InteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment2InteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        etName = view.findViewById(R.id.et_name);
        etExtraInfo = view.findViewById(R.id.et_extra_info);
        btnContinue = view.findViewById(R.id.btn_continue_frag2);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                } else if (listener != null) {
                    listener.onContinueClicked(name);
                }
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}