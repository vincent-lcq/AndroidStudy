package com.vincent.notebook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vincent.notebook.bean.Notepad;
import com.vincent.notebook.databinding.ItemListBinding;

import java.util.ArrayList;
import java.util.List;

public class NotepadListAdapter extends RecyclerView.Adapter<NotepadListAdapter.VH> {
    List<Notepad> list = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemListBinding binding = ItemListBinding.inflate(layoutInflater);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Notepad notepad = list.get(position);
        holder.binding.contentTv.setText(notepad.getContent());
        holder.binding.timeTv.setText(notepad.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<Notepad> notepadList) {
        this.list.clear();
        this.list.addAll(notepadList);
        notifyDataSetChanged();
    }

    class VH extends RecyclerView.ViewHolder {
        ItemListBinding binding;
        public VH(@NonNull ItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
