package com.bignerdranch.android.formattextapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class ShowEmphasizedTextFragment extends DialogFragment {

    private static final String ARG_EMPHASIZED_TEXT = "emphasizedtext";

    private TextView mEmphasisedText;

    public static ShowEmphasizedTextFragment newInstance(String emphasizedText) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_EMPHASIZED_TEXT, emphasizedText);

        ShowEmphasizedTextFragment fragment = new ShowEmphasizedTextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String showText = (String) getArguments().getSerializable(ARG_EMPHASIZED_TEXT);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.emphasized_text, null);

        mEmphasisedText = (TextView) v.findViewById(R.id.emphasized_text_view);
        mEmphasisedText.setText(showText);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.emphasized_text_dialog_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}

