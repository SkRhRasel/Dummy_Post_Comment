package skrasel.com.posttest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MedicationsViewHolder> {
    private List<User> users;

    public UserAdapter(Context context, List<User> item) {
        this.users = item;
    }

    @NonNull
    @Override
    public UserAdapter.MedicationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);

        return new UserAdapter.MedicationsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MedicationsViewHolder holder, int position) {

        User user = users.get(position);
        if (user == null)
            return;
        holder.lytMain.setTag(user);
        holder.tvName.setText(user.getName());
        holder.tvComments.setText(user.getComment());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MedicationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvName, tvComments;
        public ImageView ivThumb;
        public RelativeLayout lytMain;

        public MedicationsViewHolder(View view) {
            super(view);
            lytMain = (RelativeLayout) view.findViewById(R.id.lyt_main);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvComments = (TextView) view.findViewById(R.id.tvComments);
            ivThumb = (ImageView) view.findViewById(R.id.imageView);
            lytMain.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            User user = (User) v.getTag();
            if (user == null)
                return;
            switch (v.getId()) {
                case R.id.lyt_main:
                    break;
            }

        }
    }


    public void addItem(User user) {
        if (!users.contains(user)) {
            users.add(user);
            notifyDataSetChanged();
        }
    }

}
