package com.example.sumit_thakur.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sumit_thakur.studentmanagementsystem.Model.StudentInformation;

/**
 * student data entry class
 */
public class StudentDataEntry extends AppCompatActivity {
    private static final int RESULT_CODE = 22;
    private static Integer rollNumber;
    private EditText fullName, schoolName, emailId, contactNumber;
    private Button register;
    private RadioButton male, female, other;
    private String checkGender;
    private StudentInformation infoStudent;

    /**
     * @param savedInstanceState oncreate
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data_entry);
        init();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                genderSelect();
                int flag;
                flag = onRegisterPressed();
                if (flag == 1) {
                    Toast.makeText(getBaseContext(), "Register Sucessfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.putExtra("StudentInformation", infoStudent);
                    setResult(RESULT_CODE, intent);
                    finish();
                }

            }
        });

    }

    /**
     * @return integer value which satisfy the condition
     */
    public int onRegisterPressed() {
        String userEmail = emailId.getText().toString();
        String fName = fullName.getText().toString();
        String mobNumber = contactNumber.getText().toString();
        String nameSchool = schoolName.getText().toString();

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
        infoStudent.setRollNumber(Integer.toString(rollNumber));
        rollNumber++;
    }


    /**
     * initialization
     */
    public void init() {
        fullName = (EditText) findViewById(R.id.et_fullName);
        schoolName = (EditText) findViewById(R.id.et_schoolName);
        emailId = (EditText) findViewById(R.id.text_id);
        contactNumber = (EditText) findViewById(R.id.mobileNumber);
        register = (Button) findViewById(R.id.btn_register);
        checkGender = "";
        male = (RadioButton) findViewById(R.id.radio_male);
        female = (RadioButton) findViewById(R.id.radio_female);
        other = (RadioButton) findViewById(R.id.radio_others);
        rollNumber = 1;

    }

    /**
     * gender Select
     */
    public void genderSelect() {
        if (onRadioButtonClicked(male)) {
            checkGender = male.getText().toString();
        } else if (onRadioButtonClicked(female)) {
            checkGender = female.getText().toString();
        } else if (onRadioButtonClicked(other)) {
            checkGender = other.getText().toString();
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


}
