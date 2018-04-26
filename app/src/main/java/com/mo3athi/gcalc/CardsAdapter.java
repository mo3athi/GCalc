package com.mo3athi.gcalc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mo3athi.gcalc.CardsAdapter.MyViewHolder;
import java.util.List;

/**
 * Created by Mo3athi on 1/4/2018.
 */

public class CardsAdapter extends RecyclerView.Adapter<MyViewHolder> {

  private List<Grade> grades;
  private LayoutInflater inflater;

  private int[] hoursArr, gradesArr, pGradesArr;

  public CardsAdapter(Context context, List<Grade> grades) {
    inflater = LayoutInflater.from(context);
    this.grades = grades;
    hoursArr = new int[grades.size()];
    gradesArr = new int[grades.size()];
    pGradesArr = new int[grades.size()];
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.grade_card, parent, false);
    CardsAdapter.MyViewHolder viewHolder = new CardsAdapter.MyViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    Grade current = grades.get(position);
    int courseNumber = position + 1;
    holder.grade.setText(grades.get(position).getGrade());

  }

  @Override
  public int getItemCount() {
    return grades.size();
  }


  public List<Grade> getGrades() {
    return grades;
  }

  public void addNewItem(Grade grade) {
    grades.add(new Grade());
    notifyItemInserted(getItemCount() - 1);
  }


  public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView grade, hours, nameg, pre;

    public MyViewHolder(final View itemView) {
      super(itemView);
      itemView.setBackgroundColor(577482);
      grade = itemView.findViewById(R.id.tvGrade);
      hours = itemView.findViewById(R.id.tvHours);
      pre = itemView.findViewById(R.id.tvPre);
      nameg = itemView.findViewById(R.id.tvName);

    }
  }

  public void deleteAllItems() {
  }
}