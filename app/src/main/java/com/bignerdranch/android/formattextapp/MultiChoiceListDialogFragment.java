package com.bignerdranch.android.formattextapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import java.util.ArrayList;

public class MultiChoiceListDialogFragment extends DialogFragment {

    private ArrayList<Integer> mSelectedItemsIndexList;

    interface MultiChoiceDialogListener {
        void userClickedOK(ArrayList<Integer> arrayList);
        void userClickedCancel();
    }

    MultiChoiceDialogListener mDialogListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mDialogListener = (MultiChoiceDialogListener) activity;
        } catch (ClassCastException cce) {
            throw new ClassCastException("Dialog host must implement MultiChoiceDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSelectedItemsIndexList = new ArrayList<>();
        boolean[] defaultSelectionArray = {false,false,false};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("What emphasis would you like?")
                .setMultiChoiceItems(R.array.formatting_options, defaultSelectionArray,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    mSelectedItemsIndexList.add(which);
                                } else if (mSelectedItemsIndexList.contains(which)) {
                                    mSelectedItemsIndexList.remove(Integer.valueOf(which));
                                }
                            }
                        })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialogListener.userClickedOK(mSelectedItemsIndexList);
                    }
                })

                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialogListener.userClickedCancel();
                    }
                });

        return builder.create();
    }
}
