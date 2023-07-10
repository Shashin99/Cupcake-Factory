//package com.example.cupcake_factory;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//
//public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.viewHolder>{
//
//    Context context;
//    ArrayList<User> userArrayList;
//    DatabaseReference databaseReference;
//
//    public UserRecyclerAdapter(Context context, ArrayList<User> userArrayList) {
//        this.context = context;
//        this.userArrayList = userArrayList;
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//    }
//
//    @NonNull
//    @Override
//    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View view = layoutInflater.inflate(R.layout.user_item, parent, false);
//        return new viewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
//
//        User users = userArrayList.get(position);
//
//        holder.textName.setText("Name : "+users.getUsername());
//        holder.textEmail.setText("Email : "+users.getEmail());
//        holder.textAddress.setText("Address : "+users.getPostalAddress());
//        holder.textContact.setText("Contact Number : "+users.getPhone());
//
//        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public static class viewHolder extends RecyclerView.ViewHolder{
//
//       TextView textName;
//       TextView textEmail;
//       TextView textAddress;
//       TextView textContact;
//
//       Button buttonDelete;
//       Button buttonUpdate;
//
//       public viewHolder(@NonNull View itemView) {
//           super(itemView);
//
//           textName = itemView.findViewById(R.id.textName);
//           textEmail = itemView.findViewById(R.id.textEmail);
//           textAddress = itemView.findViewById(R.id.textAddress);
//           textContact = itemView.findViewById(R.id.textContact);
//
//           buttonDelete = itemView.findViewById(R.id.buttonDelete);
//           buttonUpdate = itemView.findViewById(R.id.buttonUpdate);
//
//      }
//    }
//}
