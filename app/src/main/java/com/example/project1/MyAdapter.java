package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewItem;

        public ViewHolder(View view) {
            super(view);
            textViewItem = view.findViewById(R.id.textViewItem);
        }
    }

    public MyAdapter(List<String> data) {
        this.dataSet = data;
    }

    //infla o item de layout e cria um novo view holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    //atribui valores ao viewholder criado
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewItem.setText(dataSet.get(position));
    }

    //metodo para que trabalha com o onBind para que este saiba q dados atualizar a cada viewholder
    @Override
    public int getItemCount()
    {
        return dataSet.size();
    }
}
