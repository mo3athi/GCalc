package com.mo3athi.gcalc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import belka.us.androidtoggleswitch.widgets.BaseToggleSwitch.OnToggleSwitchChangeListener;
import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private RecyclerView rv;
  private CardsAdapter adapter;
  private FloatingActionButton fab;
  private int grades[];
  private int pGrades[];
  private int hours[];
  private ToggleSwitch mToggleButton;
  private CardView mCardView;
  private int passGif[] = {R.drawable.happy1};
  private int failedGif[] = {R.drawable.sad1};
  private AddDialogListener listener;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    fab = findViewById(R.id.floatingActionButton);
    mToggleButton = findViewById(R.id.switch_btn);
    mCardView = findViewById(R.id.cardView);
    if (mToggleButton.getCheckedTogglePosition() == 1) {
      mCardView.setVisibility(View.VISIBLE);
    }

    mToggleButton.setOnToggleSwitchChangeListener(new OnToggleSwitchChangeListener() {
      @Override
      public void onToggleSwitchChangeListener(int position, boolean isChecked) {
        if (mToggleButton.getCheckedTogglePosition() == 0) {
          mCardView.setVisibility(View.VISIBLE);
        } else {
          mCardView.setVisibility(View.GONE);
        }
      }
    });

    fab.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        int sum = 0;
        grades = new int[adapter.getGrades().size()];
        hours = new int[adapter.getGrades().size()];
        pGrades = new int[adapter.getGrades().size()];

        calculate(grades, hours, pGrades);

//
//        StringBuilder msg = new StringBuilder("Grades list \n");
//        for (int i = 0; i < grades.length; i++) {
//          msg.append(
//              "Course #" + i + " : " + grades[i] + ", Hours: " + hours[i] + ",Prev: "
//                  + pGrades[i]
//                  + "\n");
//        }
//        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
//        Log.d("Mo3athii", msg.toString());
//
//        int totalGrades = 0;
//        int totalHours = 0;
//        for (int i : grades) {
//          totalGrades += i;
//        }
//        for (int i : hours) {
//          totalHours += i;
//        }
//
//        Toast.makeText(MainActivity.this, "Average: " + (totalGrades / totalHours),
//            Toast.LENGTH_SHORT).show();
//        calculate(grades, hours, pGrades);
      }

    });
    List<Grade> grades = new ArrayList<>();

    rv = findViewById(R.id.rv1);
    adapter = new CardsAdapter(getApplicationContext(), grades);
    rv.setAdapter(adapter);

    rv.setLayoutManager(new LinearLayoutManager(this));

//
//    final int grade = Integer.parseInt(egrade.getText().toString());
//    final int hours = Integer.parseInt(ehours.getText().toString());
//    final int preGrade = Integer.parseInt(epreGrade.getText().toString());
//    final String name = ename.getText().toString();

    Button button = findViewById(R.id.button);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        openDialog();

      }


    });

  }

  public void openDialog() {
    AddDialog addDialog = new AddDialog();
    addDialog.show(getSupportFragmentManager(), "Add");
  }

  public void fillArrays(int[] g, int[] h, int[] p) {
    for (int i = 0; i < adapter.getGrades().size(); i++) {
      g[i] = adapter.getGrades().get(i).getGrade();
      h[i] = adapter.getGrades().get(i).getHours();
      p[i] = adapter.getGrades().get(i).getLastGrade();
    }
  }

  public void calculate(int[] g, int[] h, int[] p) {
    float avg = 0;
    int totalHours = 0;
    fillArrays(grades, hours, pGrades);
    for (int i : h) {
      totalHours += i;
    }
    for (int i = 0; i < g.length; i++) {
      avg += g[i] * h[i];
    }
    avg = avg / totalHours;
    DecimalFormat df = new DecimalFormat("#.##");
    df.format(avg);
//    Toast.makeText(this, adapter.getGrades().size()+"", Toast.LENGTH_SHORT).show();
//    Toast.makeText(this, avg + "", Toast.LENGTH_SHORT).show();

    StringBuilder msg = new StringBuilder("");
    for (int i = 0; i < grades.length; i++) {
      msg.append(
          "Course #" + i + " : " + grades[i] + ", Hours: " + hours[i] + ",Prev: "
              + pGrades[i]
              + "\n");
    }

    new FancyGifDialog.Builder(this)
        .setTitle("Your Result:\n" + df.format(avg) + "%")
//        .setMessage(df.format(avg)+"%")
        .setPositiveBtnBackground("#FF4081")
        .setPositiveBtnText("Ok")
        .setNegativeBtnBackground("#FFA9A7A8")
        .setGifResource(R.drawable.sad1)   //Pass your Gif here
        .isCancellable(true)
        .OnPositiveClicked(new FancyGifDialogListener() {
          @Override
          public void OnClick() {
            Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
          }
        })
        .OnNegativeClicked(new FancyGifDialogListener() {
          @Override
          public void OnClick() {
            Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
          }
        })
        .build();

    adapter.deleteAllItems();
  }

}
