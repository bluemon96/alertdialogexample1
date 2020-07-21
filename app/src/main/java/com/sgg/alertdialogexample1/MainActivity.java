package com.sgg.alertdialogexample1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this).load(R.drawable.ryangood).into(imageView);
    }

    public void basic_dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("기본 다이얼 로그 제목").setMessage("⚠️기본 다이얼 로그 내용").setIcon(R.drawable.ic_avatar);

        AlertDialog dialog = builder.create();

        dialog.show();
    }
    public void button_dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("버튼 추가 다이얼 로그").setMessage("선택하세요");
        builder
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "예를 선택했습니다!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "아니요를 선택했습니다!", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "취소를 선택했습니다!", Toast.LENGTH_SHORT).show();

                    }
                });

        AlertDialog dialog = builder.create();

        dialog.show();
    }
    public void list_dialog() {
        final String[] items = getResources().getStringArray(R.array.coms);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("목록 다이얼 로그 제목");
//        builder.setMessage("선택해주세요");

        builder.setItems(R.array.coms, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(MainActivity.this, items[i]+ "을(를) 선택했습니다!", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    public void list_check_dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final ArrayList<String> selected_items = new ArrayList<>();
        final String[] items = getResources().getStringArray(R.array.coms);

        builder.setTitle("다중 선택 다이얼로그");
        builder.setMultiChoiceItems(R.array.coms, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int pos, boolean isChecked) {
                if (isChecked){
                    selected_items.add(items[pos]);
                }else {
                    selected_items.remove(pos);
                }
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int pos) {
                String SeletedItemsString = "";

                for(int i =0; i<selected_items.size();i++)
                {
                    SeletedItemsString =  SeletedItemsString + "," + selected_items.get(i);
                }

                Toast toast = Toast.makeText(getApplicationContext(), "선택 된 항목은 :" + SeletedItemsString,Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    public void list_radio_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] items = getResources().getStringArray(R.array.coms);
        final ArrayList<String> select_items = new ArrayList<>();

        select_items.add(items[0]);

        builder.setTitle("단일 선택 다이얼로그");

        builder.setSingleChoiceItems(R.array.coms, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int pos) {
                select_items.clear();
                select_items.add(items[pos]);
            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int pos) {
                Toast.makeText(MainActivity.this, select_items.get(0), Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void mydialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_signin, null);
        final EditText nameEditText = dialogView.findViewById(R.id.name);
        final EditText NicknameEditText = dialogView.findViewById(R.id.nickname);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int pos)
            {
                String name = "이름은 : " + nameEditText.getText().toString();
                String nickname = "별명은 : " + NicknameEditText.getText().toString();

                Toast.makeText(getApplicationContext(),name + "\n" + nickname, Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void onClickHandler(View v) {
        switch (v.getId()){
            case R.id.btn6:
                mydialog();
                break;
            case R.id.button5:
                list_radio_dialog();
                break;
            case R.id.button4:
                list_check_dialog();
                break;
            case R.id.button1:
                basic_dialog();
                break;
            case R.id.button2:
                button_dialog();
                break;
            case R.id.button3:
                list_dialog();
                break;
        }
    }
}

