package de.qwatrum.messagemate;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Special_charsFragment extends Fragment {
    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;
    Button button_8;
    Button button_9;
    Button button_10;
    Button button_11;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_special_chars, container, false);

        button_1 = view.findViewById(R.id.copy_1);
        button_2 = view.findViewById(R.id.copy_2);
        button_3 = view.findViewById(R.id.copy_3);
        button_4 = view.findViewById(R.id.copy_4);
        button_5 = view.findViewById(R.id.copy_5);
        button_6 = view.findViewById(R.id.copy_6);
        button_7 = view.findViewById(R.id.copy_7);
        button_8 = view.findViewById(R.id.copy_8);
        button_9 = view.findViewById(R.id.copy_9);
        button_10 = view.findViewById(R.id.copy_10);
        button_11 = view.findViewById(R.id.copy_11);


        Context context = getContext();
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

        button_1.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText("MessageMate", "\u200E");
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(getActivity(),R.string.copied, Toast.LENGTH_SHORT).show();
        });

        button_2.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText("MessageMate", "→ ← ↑ ↓");
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(getActivity(),R.string.copied, Toast.LENGTH_SHORT).show();
        });

        button_3.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText("MessageMate", "♔ ♕ ♖ ♗ ♘ ♙");
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(getActivity(),R.string.copied, Toast.LENGTH_SHORT).show();
        });

        button_4.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText("MessageMate", "♚ ♛ ♜ ♝ ♞ ♟");
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(getActivity(),R.string.copied, Toast.LENGTH_SHORT).show();
        });

        button_5.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText("MessageMate", "⚀ ⚁ ⚂ ⚃ ⚄ ⚅");
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(getActivity(),R.string.copied, Toast.LENGTH_SHORT).show();
        });

        button_6.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText("MessageMate", "═════════════");
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(getActivity(),R.string.copied, Toast.LENGTH_SHORT).show();
        });

        button_7.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText("MessageMate", "━━━━━━━━━━━━");
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(getActivity(),R.string.copied, Toast.LENGTH_SHORT).show();
        });
        button_8.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText("MessageMate", "( ͡° ͜ʖ ͡°)");
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(getActivity(),R.string.copied, Toast.LENGTH_SHORT).show();
        });

        button_9.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText("MessageMate", "ʕ•ᴥ•ʔ");
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(getActivity(),R.string.copied, Toast.LENGTH_SHORT).show();
        });

        button_10.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText("MessageMate", "✄");
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(getActivity(),R.string.copied, Toast.LENGTH_SHORT).show();
        });

        button_11.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText("MessageMate", "✔ ✘");
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(getActivity(),R.string.copied, Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}