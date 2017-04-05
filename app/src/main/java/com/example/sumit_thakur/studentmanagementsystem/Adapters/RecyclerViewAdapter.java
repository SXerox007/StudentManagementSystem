package com.example.sumit_thakur.studentmanagementsystem.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sumit_thakur.studentmanagementsystem.Model.StudentInformation;
import com.example.sumit_thakur.studentmanagementsystem.R;

import java.util.ArrayList;

/**
 *
 */
public class RecyclerViewAdapter extends android.support.v7.widget.RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<StudentInformation> studentInfo;

    /**
     * @param context     context
     * @param studentInfo arraylist of student info
     */
    public RecyclerViewAdapter(final Context context, final ArrayList<StudentInformation> studentInfo) {
        this.context = context;
        this.studentInfo = studentInfo;
    }

    /**
     * @param parent   parent
     * @param viewType viewType
     * @return view
     */
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        context = parent.getContext();
        View convertView;
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_list_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    /**
     * @param holder   holder
     * @param position position
     */
    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, final int position) {
        StudentInformation obj = studentInfo.get(position);
        holder.tvFname.setText(obj.getFullName());
        holder.tvSchool.setText(obj.getSchoolName());
        holder.tvEmailId.setText(obj.getEmailId());
        holder.tvContact.setText(obj.getContactNumber());
        holder.tvGender.setText(obj.getGenderSelect());
        holder.tvRollNo.setText(obj.getRollNumber());
    }

    /**
     * @return return size
     */
    @Override
    public int getItemCount() {
        return studentInfo.size();
    }

    /**
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFname, tvSchool, tvEmailId, tvContact, tvGender, tvRollNo;

        /**
         * @param itemView item view
         */
        public ViewHolder(final View itemView) {
            super(itemView);
            tvFname = (TextView) itemView.findViewById(R.id.tv_fullName);
            tvSchool = (TextView) itemView.findViewById(R.id.tv_school_name);
            tvEmailId = (TextView) itemView.findViewById(R.id.emaiId);
            tvContact = (TextView) itemView.findViewById(R.id.mobileNumber);
            tvGender = (TextView) itemView.findViewById(R.id.gender);
            tvRollNo = (TextView) itemView.findViewById(R.id.tv_roll_no);
        }

    }
}
