package com.example.sumit_thakur.studentmanagementsystem.Model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Student Info class
 */
public class StudentInformation implements Parcelable {
    /**
     *
     */
    public static final Creator<StudentInformation> CREATOR = new Creator<StudentInformation>() {
        /**
         *
         * @param in return in
         * @return studentInfo
         */
        @Override
        public StudentInformation createFromParcel(final Parcel in) {
            return new StudentInformation(in);
        }

        /**
         *
         * @param size size
         * @return sizeInfo
         */
        @Override
        public StudentInformation[] newArray(final int size) {
            return new StudentInformation[size];
        }
    };


    private String fullName, schoolName, emailId, contactNumber, genderSelect, rollNumber;

    /**
     * constructor student Info
     */
    public StudentInformation() {
        fullName = "";
        schoolName = "";
        emailId = "";
        contactNumber = "";
        genderSelect = "";
        rollNumber = "";
    }

    /**
     * @param in read form string
     */
    protected StudentInformation(final Parcel in) {
        fullName = in.readString();
        schoolName = in.readString();
        emailId = in.readString();
        contactNumber = in.readString();
        genderSelect = in.readString();
        rollNumber = in.readString();
    }


    /**
     * @param fullName set full name
     */
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    /**
     * @param schoolName set school name
     */
    public void setSchoolName(final String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * @param emailId set Email Name
     */
    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }

    /**
     * @param contactNumber set Contact Number
     */
    public void setContactNumber(final String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * @param genderSelect set gender Number
     */
    public void setGenderSelect(final String genderSelect) {
        this.genderSelect = genderSelect;
    }

    /**
     * @param rollNumber set roll number
     */
    public void setRollNumber(final String rollNumber) {
        this.rollNumber = rollNumber;
    }

    /**
     * @return get Full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return get school name
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * @return get Email Id
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @return get Contact Number
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @return get gender Select
     */
    public String getGenderSelect() {
        return genderSelect;
    }

    /**
     * @return get roll Number select
     */
    public String getRollNumber() {
        return rollNumber;
    }

    /**
     * @return describe contents
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @param dest  dest
     * @param flags flags
     */
    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(fullName);
        dest.writeString(schoolName);
        dest.writeString(emailId);
        dest.writeString(contactNumber);
        dest.writeString(genderSelect);
        dest.writeString(rollNumber);
    }
}

