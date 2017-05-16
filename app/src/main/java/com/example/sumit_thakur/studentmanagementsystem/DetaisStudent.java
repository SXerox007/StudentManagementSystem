package com.example.sumit_thakur.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.sumit_thakur.studentmanagementsystem.Adapters.RecyclerViewAdapter;
import com.example.sumit_thakur.studentmanagementsystem.Model.StudentInformation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Details of Student
 */
public class DetaisStudent extends BaseActivity {
    private static final int REQUEST_CODE = 2;
    private static Integer rollNumber = 0;
    private Button btnAddStudent;
    private StudentInformation studentInfo;
    private ArrayList<StudentInformation> userInfos;
    private RecyclerView rvRecyclerView;
    private Switch swChangeView;
    private Spinner spSpinner;

    /**
     * @param savedInstanceState onCreate Activity start
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detais_student);
        init();
        spinnerFunctionality();
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(DetaisStudent.this, StudentDataEntry.class);
                intent.putExtra("rollNumber", rollNumber);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    /**
     * initialization
     */
    public void init() {
        btnAddStudent = (Button) findViewById(R.id.btn_createPress);
        studentInfo = new StudentInformation();
        userInfos = new ArrayList<>();
        swChangeView = (Switch) findViewById(R.id.sw_change);
        spSpinner = (Spinner) findViewById(R.id.sp_user_sort);
        rollNumber++;
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        if (resultCode == RESULT_OK) {
            studentInfo = intent.getParcelableExtra("StudentInformation");
            userInfos.add(studentInfo);
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, userInfos);
            rvRecyclerView = (RecyclerView) findViewById(R.id.rvUserInfo);
            rvRecyclerView.setAdapter(recyclerViewAdapter);
            layoutSelection();
            rvRecyclerView.setHasFixedSize(true);
        } else {
            Toast.makeText(getBaseContext(), "Sucessfully changed", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * layout selection grid or linear
     */
    private void layoutSelection() {
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        swChangeView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    rvRecyclerView.setLayoutManager(new GridLayoutManager(DetaisStudent.this, 2));
                } else {
                    rvRecyclerView.setLayoutManager(new LinearLayoutManager(DetaisStudent.this));
                }
            }

        });
    }


    /**
     *
     */
    public void spinnerFunctionality() {
        final String text = "Sort By Id";
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.options_to_sort_student, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSpinner.setAdapter(adapter);
        spSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
                String dataSortVariable = parent.getItemAtPosition(position).toString();
                if (dataSortVariable.equals(text)) {
                    sortNames();
                } else {
                    sortIDs();
                }

            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {

            }

        });

    }

    /**
     * sort the data in names-vise
     */
    private void sortNames() {
        Collections.sort(userInfos, new Comparator<StudentInformation>() {
            @Override
            public int compare(final StudentInformation o1, final StudentInformation o2) {
                return (o1.getFullName()).compareToIgnoreCase(o2.getFullName());
            }
        });
    }

    /**
     * sort the data in contact number vise
     */
    private void sortIDs() {
        Collections.sort(userInfos, new Comparator<StudentInformation>() {
            @Override
            public int compare(final StudentInformation o1, final StudentInformation o2) {
                return (o1.getContactNumber()).compareTo(o2.getContactNumber());
            }
        });
    }


}
