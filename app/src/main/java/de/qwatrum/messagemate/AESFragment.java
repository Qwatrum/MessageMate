package de.qwatrum.messagemate;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AESFragment extends Fragment {

    TextInputEditText message;
    SwitchCompat switchCompat;
    TextInputLayout password_layout;
    TextInputEditText password;
    Button action;
    TextInputEditText result;
    Button copy;

    Boolean encrypt = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a_e_s, container, false);

        message = view.findViewById(R.id.message);
        switchCompat = view.findViewById(R.id.typeSwitch);
        password_layout = view.findViewById(R.id.password_layout);
        password = view.findViewById(R.id.password);
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
                if (!Objects.requireNonNull(password.getText()).toString().trim().isEmpty()) {

                    if (encrypt) {
                        try {
                            result.setText(Encrpyter.aes_encrypt(Objects.requireNonNull(message.getText()).toString().trim(), Objects.requireNonNull(password.getText()).toString()));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                    } else {
                        try {
                            result.setText(Encrpyter.aes_decrypt(Objects.requireNonNull(message.getText()).toString().trim(), Objects.requireNonNull(password.getText()).toString()));
                        } catch (Exception e) {
                            result.setText(R.string.decryption_failed);
                        }
                    }

                    result.setEnabled(true);
                    copy.setEnabled(true);
                }

            } else {
                password.clearFocus();
                password_layout.setErrorEnabled(true);
                password_layout.setError("Necessary");
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