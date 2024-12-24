package de.qwatrum.messagemate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EncryptionFragment extends Fragment {
    Button caeser;
    Button rsa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encryption, container, false);
        caeser = view.findViewById(R.id.encrypt_1);
        rsa = view.findViewById(R.id.encrypt_2);

        caeser.setOnClickListener(v -> {
            replaceFragment(new CaeserFragment());
        });

        rsa.setOnClickListener(v -> {
            replaceFragment(new AESFragment());
        });
        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_child_container, fragment);
        fragmentTransaction.commit();
    }
}