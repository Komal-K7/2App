package com.example.a2app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> nums = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nums.add("0");
    }
    public void OpenYoutube (View view){
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
        startActivity(intent);
    }
    public void random (View view){
        TextView screen = findViewById(R.id.textView3);
        PackageManager pm = getPackageManager();
        List<PackageInfo> pi = pm.getInstalledPackages(0);
        String x ="";
        for (PackageInfo poo: pi) {
            x += poo.packageName;
        }
        screen.setText(x);
        FeatureInfo[] fi = pm.getSystemAvailableFeatures();
    }

    //will call when button clicked
    public void zero_click (View v){
        num_list("0");
    }
    public void one_click(View v){
        num_list("1");
    }
    public void two_click(View v){
        num_list("2");
    }
    public void three_click(View v){
        num_list("3");
    }
    public void four_click(View v){
        num_list("4");
    }
    public void five_click(View v){
        num_list("5");
    }
    public void six_click(View v){
        num_list("6");
    }
    public void seven_click(View v){
        num_list("7");
    }
    public void eight_click(View v){
        num_list("8");
    }
    public void nine_click(View v){
        num_list("9");
    }
    public void dec_click(View v){
        num_list(".");
    }
    public void plus_click(View v){
        num_list("+");
    }
    public void multiply_click(View v){
        num_list("x");
    }
    public void divide_click(View v){
        num_list("/");
    }
    public void minus_click(View v){
        num_list("-");
    }

    //method updates screen
    public void update_screen(){
        //gets screen id
        TextView screen = findViewById(R.id.textView3);
        String temp = "";
        //The numbers and operations that need to be calculated get copied into temp
        for (int c=0;c<nums.size();c++) {
            temp += nums.get(c)+" ";
        }
        //checks if temp is too big to fit in screen
        if (temp.length() > 18) {
            temp = temp.substring(0, 18);
        }
        //sets the temp variable as the value for screen
        screen.setText(temp);
    }

    //checks if entered value is a number
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    //adds a number to the Arraylist of numbers to be operated on
    public void num_list(String num){
        TextView screen = findViewById(R.id.textView3);
        if (screen.getText().toString().length() < 18) {
            if (nums.size() == 0 && isNumeric(num)) {
                nums.add(num);
                update_screen();
            }
            else if (isNumeric(num) && nums.size() > 0){
                if (isNumeric(nums.get(nums.size() - 1))) {
                    nums.set(nums.size() - 1, nums.get(nums.size() - 1) + num);
                    update_screen();
                }
                else{
                    nums.add(num);
                    update_screen();
                }
            }
            else if (num.equals(".") && nums.size() > 0){
                if (!nums.get((nums.size() - 1)).contains(".") && (isNumeric(nums.get(nums.size() - 1)))){
                    nums.set(nums.size() - 1, nums.get(nums.size() - 1) + num);
                    update_screen();
                }
                else{
                    nums.add(0+num);
                    update_screen();
                }
            }

            /*else if ((isNumeric(num) || num.equals(".")) && (nums.size() > 0)) {
                if (isNumeric(nums.get(nums.size() - 1)+num)) {
                    nums.set(nums.size() - 1, nums.get(nums.size() - 1) + num);
                    update_screen();
                }
                else if (isNumeric(num)) {
                    nums.add(num);
                    update_screen();
                }
            }
            */

            else if (nums.size() != 0 && isNumeric(nums.get((nums.size() - 1)))) {
                nums.add(num);
                update_screen();
            }
        }
    }

    public void del_click(View v){
        if (nums.size() == 0){
            return;
        }
        //Creates a check var with the same value of the last button clicked
        String check = nums.get((nums.size() - 1));
        if (nums.size()!=0){
            //Removes the last button clicked from the nums list
            nums.remove(nums.size()-1);
        }
        //Checks to see if the removed value was multiple digits
        if (check.length() > 1){
            //Adds back the deleted value with the last digit removed
            nums.add(check.substring(0,check.length()-1));
        }
        update_screen();
    }

    public void eq_click(View v){
        String ans = "";
        while (nums.size()>2) {
            //Creates 2 var for the numbers to be operated on and converts them from String to doubles/rational numbers
            double num1 = Double.parseDouble(nums.get(0));
            double num2 = Double.parseDouble(nums.get(2));

            //Conducts the operation entered and converts ans back to String
            if (nums.get(1).equals("+")) {
                ans = Double.toString(num1 + num2);
            }
            else if (nums.get(1).equals("x")) {
                ans = Double.toString(num1 * num2);
            }
            else if (nums.get(1).equals("/")) {
                ans = Double.toString(num1 / num2);
            }
            else if (nums.get(1).equals("-")) {
                ans = Double.toString(num1 - num2);
            }
            else{
                return;
            }
            //Removes the calculated values from the num list and adds the answer to the num list
            nums.remove(0);nums.remove(0);nums.remove(0);
            nums.add(0,ans);
            update_screen();
        }

        //screen.setText( Integer.toString((int) eq.charAt(0) + (int) eq.charAt(2)-96));

        }
    }

    /*
        ISSUES:
       >can input multiple consecutive operations
       >num_list not in sync with display sometimes

        Missing Functions:
        >Bedmas (Correct order of operations)
        >Brackets
        >Exponents
     */

