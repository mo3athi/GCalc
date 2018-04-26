package com.mo3athi.gcalc;

/**
 * Created by Mo3athi on 1/4/2018.
 */

public class Grade {

  private String name;
  private int grade ;
  private int hours;
  private int lastGrade;

  public Grade(String name, int grade, int hours, int lastGrade) {
    this.name = name;
    this.grade = grade;
    this.hours = hours;
    this.lastGrade = lastGrade;
  }

  public Grade(){
  }

  public int getGrade() {
    return grade;
  }

  public int getHours() {
    return hours;
  }

  public int getLastGrade() {
    return lastGrade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public void setHours(int hours) {
    this.hours = hours;
  }

  public void setLastGrade(int lastGrade) {
    this.lastGrade = lastGrade;
  }
}
