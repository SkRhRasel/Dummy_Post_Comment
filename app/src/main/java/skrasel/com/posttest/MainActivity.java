package skrasel.com.posttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    private RecyclerView recyclerView;
    private User user;
    private TextView tvMore, tvComments;
    private List<User> users;
    private UserAdapter userCommentAdapter;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        db = new DatabaseHelper(this);
        List<User> users = db.getAllUsers();

        if (users.size() >= 1) {
            Collections.reverse(users);
            User user = users.get(users.size() - 1);
            List<User> lastUser = new ArrayList<>();
            lastUser.add(user);
            userCommentAdapter = new UserAdapter(this, lastUser);
            recyclerView.setAdapter(userCommentAdapter);
            tvComments.setText(String.valueOf(users.size()));

        } else {
            List<User> userList = getDummyData();
            for (int i = 0; i < userList.size(); i++) {
                db.insertUser(userList.get(i));
                userCommentAdapter = new UserAdapter(this, userList);
                recyclerView.setAdapter(userCommentAdapter);
                tvComments.setText(String.valueOf(userList.size()));

            }
        }
    }

    private void initView() {
        users = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        tvMore = findViewById(R.id.tv_more);
        tvComments = findViewById(R.id.tv_comments);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CommentActivity.class));
            }
        });
    }

    private List<User> getDummyData() {
        User user = new User();
        user.setName("Jhon Alex");
        user.setComment("Nice Picture !! ");
        users.add(user);
        return users;
    }


}
