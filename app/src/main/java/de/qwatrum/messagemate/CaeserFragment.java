package de.qwatrum.messagemate;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class CaeserFragment extends Fragment {

    TextInputEditText message;
    SwitchCompat switchCompat;
    TextInputLayout shift_layout;
    TextInputEditText shift;
    Button action;
    TextInputEditText result;
    Button copy;

    Boolean encrypt = true;

    Encrpyter encrpyter = new Encrpyter(); // Fun Fact: Also works for Decrypting

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_caeser, container, false);

        message = view.findViewById(R.id.message);
        switchCompat = view.findViewById(R.id.typeSwitch);
        shift_layout = view.findViewById(R.id.shift_layout);
        shift = view.findViewById(R.id.shift);
        action = view.findViewById(R.id.action_btn);
        result = view.findViewById(R.id.result);
        copy = view.findViewById(R.id.copy_b);


        Context context = getContext();
        assert context != null;
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);


        switchCompat.setOnClickListener(v -> {

            if (encrypt) {
                action.setText(R.string.decrypt_action_btn);
                switchCompat.setText(R.string.decrypt_switch);
            } else {
                action.setText(R.string.encrypt_action_btn);
                switchCompat.setText(R.string.encrypt_switch);
            }
            encrypt = !encrypt;
        });

        action.setOnClickListener(v -> {
            if (!Objects.requireNonNull(message.getText()).toString().trim().isEmpty()) {
                if (!Objects.requireNonNull(shift.getText()).toString().trim().isEmpty()) {

                    if (encrypt) {
                        result.setText(encrpyter.caeser(Objects.requireNonNull(message.getText()).toString().trim(), Integer.parseInt(Objects.requireNonNull(shift.getText()).toString())));
                        result.setEnabled(true);
                        copy.setEnabled(true);

                    } else {
                        result.setText(encrpyter.caeser(Objects.requireNonNull(message.getText()).toString().trim(), -(Integer.parseInt(Objects.requireNonNull(shift.getText()).toString()))));
                        result.setEnabled(true);
                        copy.setEnabled(true);
                    }

                } else if (!encrypt){
                    shift.clearFocus();
                    shift_layout.setError("Necessary");
                    shift_layout.setErrorEnabled(true);

                }
            }
        });

        copy.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText("MessageMate", Objects.requireNonNull(result.getText()).toString().trim());
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(getActivity(),R.string.copied, Toast.LENGTH_SHORT).show();
        });
        return view;
    }

}