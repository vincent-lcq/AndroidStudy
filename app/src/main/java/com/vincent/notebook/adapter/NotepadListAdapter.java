package com.vincent.notebook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.vincent.notebook.MainActivity;
import com.vincent.notebook.bean.Notepad;
import com.vincent.notebook.databinding.ItemListBinding;
import com.vincent.notebook.ui.EditActivity;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

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

        // 长按删除事件
        holder.itemView.setOnLongClickListener(view -> {
            showDeleteDialog(notepad,view.getContext());
            return false;
        });

        holder.itemView.setOnClickListener(view -> {
            MainActivity mainactivity = (MainActivity) view.getContext();
            Intent intent = new Intent(mainactivity, EditActivity.class);
            intent.putExtra("notepad", notepad);
            mainactivity.startActivityForResult(intent, 100);
        });
    }

    private void showDeleteDialog(Notepad notepad, Context context) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("温馨提示")
                .setMessage("是否确定删除")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialogInterface, i) -> {
                    MainActivity activity = (MainActivity) context;
                    activity.myDbHelper.delete(notepad);
                    activity.findAll();
                    Toasty.info(context, "操作成功", Toast.LENGTH_LONG,false).show();
                }).create();
        dialog.show();
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
