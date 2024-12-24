package de.qwatrum.messagemate;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

import java.io.IOException;
import java.util.Objects;

public class Spice_upFragment extends Fragment {
    Button modfiying_b;
    Button copy_b;
    TextInputEditText message;
    TextInputEditText modfiying;
    TextInputEditText result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spice_up, container, false);

        modfiying_b = view.findViewById(R.id.modfiying_b);
        copy_b = view.findViewById(R.id.copy_b);
        message = view.findViewById(R.id.message);
        modfiying = view.findViewById(R.id.modifying);
        result = view.findViewById(R.id.result);

        Context context = getContext();
        assert context != null;
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

        modfiying_b.setOnClickListener(v -> {
            if (!Objects.requireNonNull(message.getText()).toString().trim().isEmpty()) {
                if (!Objects.requireNonNull(modfiying.getText()).toString().trim().isEmpty()) {
                    result.setText(R.string.loading);

                    Modifier modifier= new Modifier();

                    String resultText = null;

                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    try {
                        resultText = modifier.modify(Objects.requireNonNull(message.getText()).toString().trim(), Objects.requireNonNull(modfiying.getText()).toString().trim());
                    } catch (JSONException | IOException e) {
                        throw new RuntimeException(e);
                    }

                    result.setText(resultText);
                    result.setEnabled(true);
                    copy_b.setEnabled(true);
                }
            }
        });

        copy_b.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText("MessageMate", Objects.requireNonNull(result.getText()).toString().trim());
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(getActivity(),R.string.copied, Toast.LENGTH_SHORT).show();
        });
        return view;
    }
}