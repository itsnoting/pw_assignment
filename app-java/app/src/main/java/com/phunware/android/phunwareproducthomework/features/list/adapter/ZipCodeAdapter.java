package com.phunware.android.phunwareproducthomework.features.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phunware.android.phunwareproducthomework.R;
import com.phunware.android.phunwareproducthomework.features.list.fragment.ZipCodeListFragmentDirections;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class ZipCodeAdapter extends RecyclerView.Adapter<ZipCodeAdapter.ViewHolder> {
    private List<String> items;

    public ZipCodeAdapter(List<String> items) {
        this.items = items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zipcode, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.zipCode.setText(items.get(position));

        holder.zipCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZipCodeListFragmentDirections.ShowDetail action = ZipCodeListFragmentDirections.showDetail(items.get(position));
                Navigation.findNavController(holder.zipCode).navigate(action);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView zipCode;

        public ViewHolder(@NonNull View view) {
            super(view);

            zipCode = view.findViewById(R.id.zipCodeTextView);
        }
    }
}
