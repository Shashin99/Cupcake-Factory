package com.example.cupcake_factory;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewCategoryRecyclerAdapter extends RecyclerView.Adapter<ViewCategoryRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<CategoryItem> categoryItemArrayList;
    DatabaseReference databaseReference;

    public ViewCategoryRecyclerAdapter(Context context, ArrayList<CategoryItem> categoryItemArrayList) {
        this.context = context;
        this.categoryItemArrayList = categoryItemArrayList;
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public ViewCategoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_category_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCategoryRecyclerAdapter.ViewHolder holder, int position) {
        CategoryItem categories = categoryItemArrayList.get(position);

        holder.textCategory.setText("Category: " +categories.getCategoryType());
        holder.textCake.setText("CakePack: " +categories.getCupCake());
        holder.textCost.setText("Cost: " +categories.getCost());

        holder.buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogConfirmOrder viewDialogConfirmOrder = new ViewDialogConfirmOrder();
                viewDialogConfirmOrder.showDialog(context, categories.getCategoryId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textCategory;
        TextView textCake;
        TextView textCost;

        Button buttonOrder;
        Button buttonView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textCategory = itemView.findViewById(R.id.textCategory);
            textCake = itemView.findViewById(R.id.textCake);
            textCost = itemView.findViewById(R.id.textCost);

            buttonOrder = itemView.findViewById(R.id.buttonOrder);
            buttonView = itemView.findViewById(R.id.buttonView);
        }
    }

    public class ViewDialogConfirmOrder {
        public void showDialog(Context context, String id){
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.confirm_order);

            Button buttonDelete1 = dialog.findViewById(R.id.buttonOrder1);
            Button buttonCancel1 = dialog.findViewById(R.id.buttonOrder2);

            buttonCancel1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonDelete1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseReference.child("categories").child(id).removeValue();
                    Toast.makeText(context, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }

}

