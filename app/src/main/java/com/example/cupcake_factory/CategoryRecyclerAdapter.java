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

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<CategoryItem> categoryItemArrayList;
    DatabaseReference databaseReference;

    public CategoryRecyclerAdapter(Context context, ArrayList<CategoryItem> categoryItemArrayList) {
        this.context = context;
        this.categoryItemArrayList = categoryItemArrayList;
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public CategoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.category_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerAdapter.ViewHolder holder, int position) {
        CategoryItem categories = categoryItemArrayList.get(position);

        holder.textCategory.setText("Category: " +categories.getCategoryType());
        holder.textCake.setText("CakePack: " +categories.getCupCake());
        holder.textCost.setText("Cost: " +categories.getCost());

        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
                viewDialogUpdate.showDialog(context, categories.getCategoryId(), categories.getCategoryType(), categories.getCupCake(), categories.getCost());
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogConfirmDelete viewDialogConfirmDelete = new ViewDialogConfirmDelete();
                viewDialogConfirmDelete.showDialog(context, categories.getCategoryId());
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

        Button buttonDelete;
        Button buttonUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textCategory = itemView.findViewById(R.id.textCategory);
            textCake = itemView.findViewById(R.id.textCake);
            textCost = itemView.findViewById(R.id.textCost);

            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonUpdate = itemView.findViewById(R.id.buttonUpdate);
        }
    }

    public class ViewDialogUpdate {
        public void showDialog(Context context, String id, String category, String cake, String cost){
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.activity_alert_dialog_add_new_category);

            EditText textCategory = dialog.findViewById(R.id.textCategory);
            EditText textCake = dialog.findViewById(R.id.textCake);
            EditText textCost = dialog.findViewById(R.id.textCost);

            textCategory.setText(category);
            textCake.setText(cake);
            textCost.setText(cost);

            Button buttonUpdate = dialog.findViewById(R.id.buttonAdd);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonUpdate.setText("UPDATE");
            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String newCategory  = textCategory.getText().toString();
                    String newCake = textCake.getText().toString();
                    String newCost = textCost.getText().toString();

                    if(category.isEmpty() || cake.isEmpty() || cost.isEmpty()) {
                        Toast.makeText(context, "Please enter all data...", Toast.LENGTH_SHORT).show();
                    } else {

                        if(newCategory.equals(category) && newCake.equals(cake) && newCost.equals(cost)){
                            Toast.makeText(context, "You don't change anything ", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child("categories").child(id).setValue(new CategoryItem(id, newCategory, newCake, newCost));
                            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }

    public class ViewDialogConfirmDelete {
        public void showDialog(Context context, String id){
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.view_dialog_confirm_delete);

            Button buttonDelete = dialog.findViewById(R.id.buttonDelete);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseReference.child("categories").child(id).removeValue();
                    Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }
}
