package com.example.assignment1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    private static final String ARG_USER_NAME = "arg_user_name";

    public interface OnFragment3InteractionListener {
        void onAgreementCheckChanged(boolean isChecked);
        void onFinishClicked(String userName);
    }

    private OnFragment3InteractionListener listener;
    private String userName;
    private TextView tvWelcomeUser;
    private CheckBox cbAgree;
    private Button btnFinish;

    public Fragment3() {
    }

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_USER_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment3InteractionListener) {
            listener = (OnFragment3InteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment3InteractionListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString(ARG_USER_NAME);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        tvWelcomeUser = view.findViewById(R.id.tv_welcome_user);
        cbAgree = view.findViewById(R.id.cb_agree);
        btnFinish = view.findViewById(R.id.btn_finish_frag3);

        if (userName != null && !userName.isEmpty()) {
            tvWelcomeUser.setText("Welcome, " + userName);
        }

        btnFinish.setEnabled(false);
        btnFinish.setText("Continue");

        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnFinish.setEnabled(isChecked);
                if (isChecked) {
                    btnFinish.setText("Finish");
                } else {
                    btnFinish.setText("Continue");
                }

                if (listener != null) {
                    listener.onAgreementCheckChanged(isChecked);
                }
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onFinishClicked(userName);
                } else {
                    Toast.makeText(getContext(), "Onboarding completed for " + userName, Toast.LENGTH_SHORT).show();
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