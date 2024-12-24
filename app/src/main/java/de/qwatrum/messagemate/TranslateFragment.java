package de.qwatrum.messagemate;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;

import java.io.IOException;
import java.util.Objects;

public class TranslateFragment extends Fragment {
    Button translate_b;
    Button copy_b;
    TextInputEditText message;
    TextInputEditText language;
    TextInputLayout language_layout;
    TextInputEditText result;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate, container, false);

        translate_b = view.findViewById(R.id.translate_btn);
        copy_b = view.findViewById(R.id.copy_btn);
        message = view.findViewById(R.id.message);
        language = view.findViewById(R.id.language);
        language_layout = view.findViewById(R.id.language_layout);
        result = view.findViewById(R.id.result);

        Context context = getContext();
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

        translate_b.setOnClickListener(v -> {
            if (!Objects.requireNonNull(message.getText()).toString().trim().isEmpty()) {
                if (!Objects.requireNonNull(language.getText()).toString().trim().isEmpty()) {
                    result.setText(R.string.loading);

                    Translator translator = new Translator();
                    String resultText = null;

                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    try {
                        resultText = translator.translate(Objects.requireNonNull(message.getText()).toString().trim(), Objects.requireNonNull(language.getText()).toString().trim());
                    } catch (JSONException | IOException e) {
                        throw new RuntimeException(e);
                    }

                    result.setText(resultText);
                    result.setEnabled(true);
                    copy_b.setEnabled(true);
                } else {
                    language.clearFocus();
                    language_layout.setError("Necessary");
                    language_layout.setErrorEnabled(true);

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