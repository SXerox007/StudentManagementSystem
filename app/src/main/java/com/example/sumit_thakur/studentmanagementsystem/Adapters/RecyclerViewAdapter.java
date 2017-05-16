package com.example.sumit_thakur.studentmanagementsystem.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sumit_thakur.studentmanagementsystem.Model.StudentInformation;
import com.example.sumit_thakur.studentmanagementsystem.R;
import com.example.sumit_thakur.studentmanagementsystem.StudentDataEntry;

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
    public final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvFname, tvSchool, tvEmailId, tvContact, tvGender, tvRollNo;

        /**
         * @param itemView item view
         */
        private ViewHolder(final View itemView) {
            super(itemView);
            this.tvFname = (TextView) itemView.findViewById(R.id.tv_fullName);
            this.tvSchool = (TextView) itemView.findViewById(R.id.tv_school_name);
            this.tvEmailId = (TextView) itemView.findViewById(R.id.emaiId);
            this.tvContact = (TextView) itemView.findViewById(R.id.mobileNumber);
            this.tvGender = (TextView) itemView.findViewById(R.id.gender);
            this.tvRollNo = (TextView) itemView.findViewById(R.id.tv_roll_no);
            itemView.setOnClickListener(this);
        }

        /**
         * @param v view
         */
        public void onClick(final View v) {
            final int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                builder.setTitle(R.string.dialog_fire_missiles);
                builder.setPositiveButton(R.string.view_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int id) {
                        Intent intent = new Intent(context, StudentDataEntry.class);
                        intent.putExtra("key", "view");
                        intent.putExtra("object", studentInfo.get(pos));
                        context.startActivity(intent);
                    }
                });
                builder.setNegativeButton(R.string.edit_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int id) {
                        Intent intent = new Intent(context, StudentDataEntry.class);
                        intent.putExtra("object", studentInfo.get(pos));
                        intent.putExtra("position", pos);
                        ((Activity) context).startActivityForResult(intent, 2);
                    }
                });
                builder.setNeutralButton(R.string.delete_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int id) {
                        studentInfo.remove(pos);
                        notifyDataSetChanged();
                    }
                });
                builder.create();
                builder.show();
            }
        }
    }
}
