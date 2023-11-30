package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<String> dataSet = new ArrayList<>();
        dataSet.add("Item 1");
        dataSet.add("Item 2");
        dataSet.add("Item 3");
        dataSet.add("Item 4");
        dataSet.add("Item 5");
        dataSet.add("Item 6");
        dataSet.add("Item 7");
        dataSet.add("Item 8");
        dataSet.add("Item 9");
        dataSet.add("Item 10");
        dataSet.add("Item 11");
        dataSet.add("Item 12");
        dataSet.add("Item 13");
        dataSet.add("Item 14");
        dataSet.add("Item 15");
        dataSet.add("Item 16");
        dataSet.add("Item 17");
        dataSet.add("Item 18");
        dataSet.add("Item 19");
        dataSet.add("Item 20");

        //Inicialização do recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Anexando o adaptador
        adapter = new MyAdapter(dataSet);
        recyclerView.setAdapter(adapter);

        //instanciação do ITH q usa o simplecallback(para o uso dos metodos onMove e onSwiped)
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            //usado para o dragNdrop
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();

                Collections.swap(dataSet, fromPosition, toPosition);
                adapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            //trata dos gestos esquerda e direita
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    int position = viewHolder.getAdapterPosition();
                    if(direction == ItemTouchHelper.LEFT){
                        dataSet.remove(position);//esquerda elimina item
                        adapter.notifyItemRemoved(position);
                    }else if (direction == ItemTouchHelper.RIGHT){
                        showEditDialog(dataSet, position);//direita chama o metodo showEditDialog
                    }
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    //metodo q permite a edição de um item(texto de um viewHolder) dado o conj de itens e a posição de cada um
    private void showEditDialog(List<String> dataSet, int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit this Item");

        final EditText input = new EditText(this);
        input.setText(dataSet.get(position));
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, wich) -> {
            String editedText = input.getText().toString().trim();
            dataSet.set(position, editedText);
            adapter.notifyItemChanged(position);//clicando o botão ok a atualização de texto é realizada
        });

        builder.setNegativeButton("Cancel", (dialog, wich) -> dialog.cancel());//clicando no botão de cancelar nenhuma atualização de texto é guardada

        builder.show();
    }
}