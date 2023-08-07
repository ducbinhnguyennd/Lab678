package com.example.lab5;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<User> {
    private LayoutInflater inflater;

    private ArrayList<User> list;
    private MainActivity mainActivity;


    public CustomAdapter(Context context, List<User> userList) {
        super(context, 0, userList);
        inflater = LayoutInflater.from(context);
        
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item1, parent, false);

        }
        User u = getItem(position);
        TextView tvName = view.findViewById(R.id.tv_username);
        TextView tvPass = view.findViewById(R.id.tv_passwd);
        TextView tvEmail = view.findViewById(R.id.tv_email);
        TextView tvFullname = view.findViewById(R.id.tv_fullname);

        tvName.setText(u.getUsername());
        tvPass.setText(u.getPasswd());
        tvEmail.setText(u.getEmail());
        tvFullname.setText(u.getFullname());


        return view;
    }
}

