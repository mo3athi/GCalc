package com.mo3athi.gcalc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import java.util.ArrayList;
import java.util.List;

public class AddDialog extends AppCompatDialogFragment implements AddDialogListener {

  private TextInputEditText egrade;
  private TextInputEditText ehours;
  private TextInputEditText ename;
  private TextInputEditText epreGrade;
  private CheckBox isRepeated;
  private AddDialogListener listener;
  private List<Grade> gradesList = new ArrayList<Grade>();
  private CardsAdapter adapter;

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.course_dialog, null);
    egrade = view.findViewById(R.id.etGrade);
    ehours = view.findViewById(R.id.etHours);
    ename = view.findViewById(R.id.etName);
    epreGrade = view.findViewById(R.id.etPGrade);
    isRepeated = view.findViewById(R.id.isRepeated);
    builder.setView(view).setTitle("Add Course Grade")
        .setPositiveButton("Ok",
            new OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {

                final int grade = Integer.parseInt(egrade.getText().toString());
                final int hours = Integer.parseInt(ehours.getText().toString());
                final int preGrade = Integer.parseInt(epreGrade.getText().toString());
                final String name = ename.getText().toString();
                listener.getDialogData(name, grade, hours, preGrade);
              }
            })
        .setNegativeButton("Cancel", new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {

          }
        });

    return builder.create();
  }


  @Override
  public void getDialogData(String courseName, int grade, int hours, int previous) {
    gradesList.add(new Grade(courseName, grade, hours, previous));
    adapter.addNewItem(new Grade(courseName, grade, hours, previous));
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    try {
      listener = (AddDialogListener) context;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
