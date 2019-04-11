package skrasel.com.posttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private EditText edtComment;
    private UserAdapter userCommentAdapter;
    private ImageView btnSend;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_layout);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Comments");
        // add back arrow to toolbar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        initView();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerViewAllComment);
        edtComment = findViewById(R.id.edtComment);
        btnSend = findViewById(R.id.btnSend);

        db = new DatabaseHelper(this);
        List<User> users = db.getAllUsers();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userCommentAdapter = new UserAdapter(this, users);
        recyclerView.setAdapter(userCommentAdapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comments = edtComment.getText().toString();
                if (!TextUtils.isEmpty(comments)) {
                    User user = new User();
                    user.setName("Sk Rasel");
                    user.setComment(comments);
                    db.insertUser(user);
                    userCommentAdapter.addItem(user);
                    edtComment.setText("");

                }
            }
        });
    }
}
