package com.example.sumit_thakur.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sumit_thakur.studentmanagementsystem.Model.StudentInformation;

/**
 * Add student data entry class
 */
public class StudentDataEntry extends BaseActivity {
    private EditText etFullName, etSchoolName, etEmailId, etContactNumber;
    private Button btnRegister;
    private TextView tvRollNumber;
    private RadioButton rba, rbb, rbc, rbd;
    private String checkGender, datacheck, data, rollNumber;
    private StudentInformation infoStudent;

    /**
     * @param savedInstanceState oncreate
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data_entry);
        init();
        if (infoStudent != null) {
            setData();
            Intent intent = getIntent();
            data = intent.getStringExtra("key");
            if (data.equals(datacheck)) {
                etFullName.setEnabled(false);
                etSchoolName.setEnabled(false);
                etEmailId.setEnabled(false);
                etContactNumber.setEnabled(false);
                btnRegister.setVisibility(View.GONE);
            } else {
                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        genderSelect();
                        int flag;
                        flag = onRegisterPressed();
                        if (flag == 1) {
                            Intent intent = getIntent();
                            //code to do


                            finish();
                        }
                    }
                });
            }
        } else {
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    genderSelect();
                    int flag;
                    flag = onRegisterPressed();
                    if (flag == 1) {
                        Toast.makeText(getBaseContext(), "Register Sucessfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.putExtra("StudentInformation", infoStudent);
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                }
            });
        }
    }


    /**
     * @return integer value which satisfy the condition
     */
    public int onRegisterPressed() {
        String userEmail = etEmailId.getText().toString();
        String fName = etFullName.getText().toString();
        String mobNumber = etContactNumber.getText().toString();
        String nameSchool = etSchoolName.getText().toString();

        if (userEmail.isEmpty() || nameSchool.isEmpty() || fName.isEmpty()
                || checkGender.isEmpty() || mobNumber.isEmpty()) {
            Toast.makeText(getBaseContext(), "All field Mendatatory", Toast.LENGTH_LONG).show();
            return 2;
        } else {
            boolean flag = isValidEmail(userEmail);
            if (!flag) {
                Toast.makeText(getBaseContext(), "Email not Valid..!!!  Please Enter the valid Email.", Toast.LENGTH_LONG).show();
            } else {
                setDataUserInfo(userEmail, fName, nameSchool, mobNumber);
                return 1;
            }
        }


        return 0;
    }

    /**
     * @param userEmail  email
     * @param fName      full name
     * @param nameSchool school name
     * @param mobNumber  mobile number
     */
    public void setDataUserInfo(final String userEmail, final String fName, final String nameSchool, final String mobNumber) {
        infoStudent = new StudentInformation();
        infoStudent.setEmailId(userEmail);
        infoStudent.setContactNumber(mobNumber);
        infoStudent.setFullName(fName);
        infoStudent.setSchoolName(nameSchool);
        infoStudent.setGenderSelect(checkGender);
    }


    /**
     * initialization
     */
    public void init() {
        tvRollNumber = (TextView) findViewById(R.id.tv_roll_no);
        etFullName = (EditText) findViewById(R.id.et_fullName);
        etSchoolName = (EditText) findViewById(R.id.et_schoolName);
        etEmailId = (EditText) findViewById(R.id.text_id);
        etContactNumber = (EditText) findViewById(R.id.mobileNumber);
        btnRegister = (Button) findViewById(R.id.btn_register);
        checkGender = "";
        rba = (RadioButton) findViewById(R.id.radio_a);
        rbb = (RadioButton) findViewById(R.id.radio_b);
        rbc = (RadioButton) findViewById(R.id.radio_c);
        rbd = (RadioButton) findViewById(R.id.radio_d);
        infoStudent = getIntent().getParcelableExtra("object");
        datacheck = "view";

    }


    /**
     * gender Select
     */
    public void genderSelect() {
        if (onRadioButtonClicked(rba)) {
            checkGender = rba.getText().toString();
        } else if (onRadioButtonClicked(rbb)) {
            checkGender = rbb.getText().toString();
        } else if (onRadioButtonClicked(rbc)) {
            checkGender = rbc.getText().toString();
        } else if (onRadioButtonClicked(rbd)) {
            checkGender = rbd.getText().toString();
        }
    }

    /**
     * @param view ischecked
     * @return gender select
     */
    public boolean onRadioButtonClicked(final View view) {
        boolean flag = ((RadioButton) view).isChecked();
        return flag;
    }

    /**
     * @param email checkEmail
     * @return return the email valid or not
     */
    private static boolean isValidEmail(final String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * set the data in different different fields
     */
    private void setData() {
        etFullName.setText(infoStudent.getFullName());
        etSchoolName.setText(infoStudent.getSchoolName());
        etEmailId.setText(infoStudent.getEmailId());
        etContactNumber.setText(infoStudent.getContactNumber());
        btnRegister.setText("Save");
        btnRegister.setVisibility(View.GONE);
    }
}
