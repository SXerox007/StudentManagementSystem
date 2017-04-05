package com.example.sumit_thakur.studentmanagementsystem.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sumit_thakur.studentmanagementsystem.Model.StudentInformation;
import com.example.sumit_thakur.studentmanagementsystem.R;

import java.util.ArrayList;

/**
 * Created by SUMIT_THAKUR on 04/04/17.
 */

public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<StudentInformation> userInfos;

    /**
     * @param context   context
     * @param userInfos userInfo
     */
    public ListViewAdapter(final Context context, final ArrayList<StudentInformation> userInfos) {
        this.context = context;
        this.userInfos = userInfos;
    }

    /**
     * @return userInfo size
     */
    @Override
    public int getCount() {
        return userInfos.size();
    }

    /**
     * @param position position
     * @return return position
     */
    @Override
    public Object getItem(final int position) {
        return userInfos.get(position);
    }

    /**
     * @param position position
     * @return return the position
     */
    @Override
    public long getItemId(final int position) {
        return position;
    }

    /**
     * @param position    postion
     * @param convertView view
     * @param parent      parent
     * @return convertView
     */
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view;

        view = LayoutInflater.from(context).inflate(R.layout.activity_list_view, parent, false);

        StudentInformation userInfo = (StudentInformation) getItem(position);
        TextView fullName = (TextView) view.findViewById(R.id.tv_fullName);
        TextView schoolName = (TextView) view.findViewById(R.id.tv_school_name);
        TextView rollNumber = (TextView) view.findViewById(R.id.tv_roll_no);
        TextView emailId = (TextView) view.findViewById(R.id.emaiId);
        TextView contactNumber = (TextView) view.findViewById(R.id.mobileNumber);
        TextView sex = (TextView) view.findViewById(R.id.gender);

        Log.d("hello", "cool");
        Log.d("hello", userInfo.getEmailId());

        fullName.setText(userInfo.getFullName());
        schoolName.setText(userInfo.getSchoolName());
        rollNumber.setText(userInfo.getRollNumber());
        emailId.setText(userInfo.getEmailId());
        contactNumber.setText(userInfo.getContactNumber());
        sex.setText(userInfo.getGenderSelect());

        return view;
    }
}
