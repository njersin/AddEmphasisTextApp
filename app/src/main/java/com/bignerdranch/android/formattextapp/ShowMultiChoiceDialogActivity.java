package com.bignerdranch.android.formattextapp;

import android.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ShowMultiChoiceDialogActivity extends FragmentActivity
        implements MultiChoiceListDialogFragment.MultiChoiceDialogListener {

    private EditText mEmphasisText;
    private Button mAddEmphasisButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_multichoice_dialog);

        mEmphasisText = (EditText) findViewById(R.id.emphasis_text_field);

        mAddEmphasisButton = (Button) findViewById(R.id.add_emphasis_button);
        mAddEmphasisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMultiDialog();
            }
        });
    }

    public void showMultiDialog() {
        DialogFragment dialog = new MultiChoiceListDialogFragment();
        dialog.show(getFragmentManager(), "MultiChoiceListDialogFragment");
    }

    public void showEmphasizedTextDialog(String emphasizedText) {
        DialogFragment emphasizedFragment = ShowEmphasizedTextFragment.newInstance(emphasizedText);
        emphasizedFragment.show(getFragmentManager(), "EmphasizedTextFragment");
    }

    public void userClickedOK(ArrayList<Integer> selectedOptions) {

        String normalText = mEmphasisText.getText().toString();

        if (selectedOptions.size() != 0) {
            for (int i = 0; i < selectedOptions.size(); i++) {
                int choice = selectedOptions.get(i);
                switch (choice) {
                    case 0:
                        normalText = normalText.toUpperCase();
                        break;
                    case 1:
                        normalText = normalText + "!!!";
                        break;
                    case 2:
                        normalText = normalText + " :-)";
                        break;
                }
            }
            showEmphasizedTextDialog(normalText);
        }
    }

    public void userClickedCancel() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_multi_choice_dialog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
