package com.example.lostandfound;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ItemDetailsFragment extends BottomSheetDialogFragment {

    private final ItemModel item;
    private final DatabaseHelper dbHelper;
    private final Runnable onItemDeleted;

    public ItemDetailsFragment(ItemModel item, DatabaseHelper dbHelper, Runnable onItemDeleted) {
        this.item = item;
        this.dbHelper = dbHelper;
        this.onItemDeleted = onItemDeleted;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);

        TextView title = view.findViewById(R.id.detailTitle);
        TextView description = view.findViewById(R.id.detailDescription);
        TextView status = view.findViewById(R.id.detailStatus);
        Button deleteBtn = view.findViewById(R.id.deleteButton);

        title.setText("Name: " + item.getName());
        description.setText(item.getDescription());
        status.setText("Status: " + item.getStatus());

        deleteBtn.setOnClickListener(v -> {
            dbHelper.deleteItem(item.getId());
            dismiss();
            if (onItemDeleted != null) onItemDeleted.run();
        });

        return view;
    }
}
