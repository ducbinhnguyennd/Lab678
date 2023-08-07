package com.example.lab5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    static final String PEOPLE_URL = "http://192.168.1.252:3000/";
    ListView listView;
    List<User> list = new ArrayList<>();
    CustomAdapter adapter;

    private User selectedUser;
    private String selectedUserId;
    private FloatingActionButton btn_add;
    private Button btn_edit, btn_delete;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        btn_add = findViewById(R.id.btn_add);
        btn_edit = findViewById(R.id.bt_edit);
        listView = findViewById(R.id.lvUser);
        adapter = new CustomAdapter(this, list);
        listView.setAdapter(adapter);
        getListUser();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddUserDialog();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy thông tin người dùng được chọn từ danh sách
                selectedUser = (User) parent.getItemAtPosition(position);
                // Lấy id của item được chọn
                selectedUserId = selectedUser.get_id();
                // Hiển thị dialog để cho người dùng cập nhật thông tin
                showAEditUserDialog(selectedUser);
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiểm tra xem người dùng đã chọn một item trong ListView chưa
                if (selectedUser != null) {
                    // Hiển thị Dialog cập nhật thông tin người dùng
                    showAEditUserDialog(selectedUser);
                } else {
                    // Nếu người dùng chưa chọn item nào, bạn có thể hiển thị thông báo hoặc thực hiện hành động khác tùy ý
                    Toast.makeText(MainActivity.this, "Vui lòng chọn người dùng cần cập nhật", Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Lấy thông tin người dùng được chọn từ danh sách
                selectedUser = (User) adapterView.getItemAtPosition(position);
                // Lấy id của item được chọn
                selectedUserId = selectedUser.get_id();

                // Hiển thị dialog xác nhận trước khi xóa
                showDeleteConfirmationDialog();

                // Trả về true để ngăn việc chọn ngắn gây ra sự kiện onItemClick tại cùng một thời điểm.
                return true;
            }
        });
    }
    void getListUser(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PEOPLE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ServiceAPI serviceAPI = retrofit.create(ServiceAPI.class);
        Call<List<User>> userCall = serviceAPI.getAllUser();
        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    list.clear();
                    list.addAll( response.body() );
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void AddUser(User userDTO){
        // tạo đói tượng chuyển đổi kiểu dũ liệu
        Gson gson = new GsonBuilder().setLenient().create();
        // tạo đối tượng retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PEOPLE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
// khởi tạo interface
        ServiceAPI userInterface = retrofit.create(ServiceAPI.class);


        // tạo call
        Call<User> objCall = userInterface.createUser(userDTO);
// thực hiện gửi dữ liệu lên sv
        objCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // kết quả server trả về ở đây
                if (response.isSuccessful()){
                    // lấy kết quả trả về
                    User userDTO = response.body();
                    Toast.makeText(MainActivity.this, "Kết quả" + userDTO.getUsername() +", id ="+ userDTO.get_id(), Toast.LENGTH_SHORT).show();
                }else {
                    Log.e("zzzzzzzzzzz", response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // nếu xảy ra lỗi sẽ thông báo ở đây
                Log.e("zzzzzzzzz", t.getLocalizedMessage() );
            }
        });

    }

    void EditUser(String userIdToUpdate, User userDTO){
        // tạo đói tượng chuyển đổi kiểu dũ liệu
        Gson gson = new GsonBuilder().setLenient().create();
        // tạo đối tượng retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PEOPLE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
// khởi tạo interface
        ServiceAPI userInterface = retrofit.create(ServiceAPI.class);

        // tạo call
        Call<User> objCall = userInterface.updateUser(userIdToUpdate,userDTO);
// thực hiện gửi dữ liệu lên sv
        objCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // kết quả server trả về ở đây
                if (response.isSuccessful()){
                    // lấy kết quả trả về
                    User userDTO = response.body();
                    Toast.makeText(MainActivity.this, "Kết quả" + userDTO.getUsername() +", id ="+ userDTO.get_id(), Toast.LENGTH_SHORT).show();
                }else {
                    Log.e("zzzzzzzzzzz", response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // nếu xảy ra lỗi sẽ thông báo ở đây
                Log.e("zzzzzzzzz", t.getLocalizedMessage() );
            }
        });

    }

    private void deleteUser(User user) {
        // Tạo đối tượng Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PEOPLE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Khởi tạo interface ServiceAPI
        ServiceAPI serviceAPI = retrofit.create(ServiceAPI.class);

        // Lấy id của người dùng cần xóa
        String userIdToDelete = user.get_id();

        // Gọi API xóa người dùng
        Call<Void> call = serviceAPI.deleteUser(userIdToDelete);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Xóa thành công, cập nhật lại danh sách người dùng
                    getListUser();
                } else {
                    // Xử lý lỗi nếu xảy ra lỗi khi xóa
                    Toast.makeText(MainActivity.this, "Lỗi khi xóa người dùng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Xử lý lỗi nếu gặp lỗi kết nối
                Toast.makeText(MainActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void refreshData() {
        getListUser();
    }
    private void showAddUserDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_user, null);
        dialogBuilder.setView(dialogView);

        EditText edtName = dialogView.findViewById(R.id.edtName);
        EditText edtPasswd = dialogView.findViewById(R.id.edtPasswd);
        EditText edtEmail = dialogView.findViewById(R.id.edtEmail);
        EditText edtFullname = dialogView.findViewById(R.id.edtFullname);
        Button btnSave = dialogView.findViewById(R.id.btnSave);


        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString();
                String passwd = edtPasswd.getText().toString();
                String email = edtEmail.getText().toString();
                String fullname = edtFullname.getText().toString();

                // Thêm người dùng mới vào ListView
                User newUser = new User(name, passwd, email, fullname);
                AddUser(newUser);
                refreshData();
                dialog.dismiss();
            }
        });
    }
    private void showAEditUserDialog(User selectedUser) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_user, null);
        dialogBuilder.setView(dialogView);

        EditText edtName = dialogView.findViewById(R.id.edtName);
        EditText edtPasswd = dialogView.findViewById(R.id.edtPasswd);
        EditText edtEmail = dialogView.findViewById(R.id.edtEmail);
        EditText edtFullname = dialogView.findViewById(R.id.edtFullname);
        Button btnSave = dialogView.findViewById(R.id.btnSave);

        // Đặt thông tin của người dùng đã chọn vào các trường trong dialog.
        edtName.setText(selectedUser.getUsername());
        edtPasswd.setText(selectedUser.getPasswd());
        edtEmail.setText(selectedUser.getEmail());
        edtFullname.setText(selectedUser.getFullname());

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin đã cập nhật từ các trường trong dialog.
                String updatedName = edtName.getText().toString();
                String updatedPasswd = edtPasswd.getText().toString();
                String updatedEmail = edtEmail.getText().toString();
                String updatedFullname = edtFullname.getText().toString();

                // Tạo đối tượng User mới với thông tin đã cập nhật.
                User updatedUser = new User(updatedName, updatedPasswd, updatedEmail, updatedFullname);

                // Gọi phương thức EditUser để cập nhật thông tin người dùng.
                EditUser(selectedUserId, updatedUser);
                refreshData();
                dialog.dismiss();
            }
        });
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa người dùng này?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Thực hiện xóa người dùng khi người dùng chọn "Đồng ý"
                deleteUser(selectedUser);
                // Cập nhật lại ListView sau khi xóa
                refreshData();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Không thực hiện gì khi người dùng chọn "Hủy"
                dialog.dismiss();
            }
        });
        builder.show();
    }
}