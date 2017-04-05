package com.example.sumit_thakur.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.sumit_thakur.studentmanagementsystem.Adapters.RecyclerViewAdapter;
import com.example.sumit_thakur.studentmanagementsystem.Model.StudentInformation;

import java.util.ArrayList;

/**
 * Details of Student
 */
public class DetaisStudent extends AppCompatActivity {
    private static final int REQUEST_CODE = 2, RESULT_CODE = 22;
    private Button addStudent;
    private StudentInformation studentInfo;
    private ArrayList<StudentInformation> userInfos;
    private RecyclerView recyclerView;
    private Switch changeView;

    /**
     * @param savedInstanceState onCreate Activity start
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detais_student);
        init();

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(DetaisStudent.this, StudentDataEntry.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    /**
     * initialization
     */
    public void init() {
        addStudent = (Button) findViewById(R.id.btn_createPress);
        studentInfo = new StudentInformation();
        userInfos = new ArrayList<>();
        changeView = (Switch) findViewById(R.id.sw_change);
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        if (resultCode == RESULT_CODE) {
            studentInfo = intent.getParcelableExtra("StudentInformation");
            userInfos.add(studentInfo);
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, userInfos);
            recyclerView = (RecyclerView) findViewById(R.id.rvUserInfo);
            recyclerView.setAdapter(recyclerViewAdapter);
            layoutSelection();
            recyclerView.setHasFixedSize(true);
        } else {
            Toast.makeText(getBaseContext(), "result code Not Valid", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * layout selection grid or linear
     */
    private void layoutSelection() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        changeView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    recyclerView.setLayoutManager(new GridLayoutManager(DetaisStudent.this, 2));
                } else {
                    recyclerView.setLayoutManager(new LinearLayoutManager(DetaisStudent.this));
                }
            }

        });


    }

}
