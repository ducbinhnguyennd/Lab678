package com.example.lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        }

        // Get the user at the current position
        User user = userList.get(position);

        // Set the user details to the appropriate TextViews in the custom layout
        TextView textViewUserId = convertView.findViewById(R.id.textViewUserId);
        textViewUserId.setText("User ID: " + user.get_id());

        TextView textViewUsername = convertView.findViewById(R.id.textViewUsername);
        textViewUsername.setText("Username: " + user.getUsername());

        // Add more TextViews and set other user details as needed

        return convertView;
    }
}