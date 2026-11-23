package com.example.assignment1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity
        implements Fragment2.OnFragment2InteractionListener,
        Fragment3.OnFragment3InteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            openFragment(new Fragment2(), false);
        }
    }

    private void openFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void onContinueClicked(String userName) {
        Fragment3 fragment3 = Fragment3.newInstance(userName);
        openFragment(fragment3, true);
    }

    @Override
    public void onAgreementCheckChanged(boolean isChecked) {
    }

    @Override
    public void onFinishClicked(String userName) {
        Toast.makeText(this, "Welcome " + userName + ", setup completed!", Toast.LENGTH_LONG).show();
    }
}