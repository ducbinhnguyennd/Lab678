package com.example.lab5.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5.R;
import com.example.lab5.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private final List<User> listUser;

    public UserAdapter(List<User> listUser) {
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = listUser.get(position);
        if (user== null){
            return;
        }
        holder.tv_usname.setText(user.getUsername());
        holder.tv_passwd.setText(user.getPasswd());
        holder.tv_email.setText(user.getEmail());
        holder.tv_fullname.setText(user.getFullname());
    }

    @Override
    public int getItemCount() {
        if (listUser !=null){
            return listUser.size();
        }
        return 0;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_usname, tv_passwd, tv_email, tv_fullname;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_usname = itemView.findViewById(R.id.tv_username);
            tv_passwd = itemView.findViewById(R.id.tv_passwd);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_fullname = itemView.findViewById(R.id.tv_fullname);
        }
    }

}
