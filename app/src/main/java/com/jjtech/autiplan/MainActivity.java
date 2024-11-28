package com.jjtech.autiplan;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private ImageView calendar;
    private ImageView profile;
    private ImageView home;
    private ImageView rewards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Café da manhã", R.drawable.cafe));
        itemList.add(new Item("Escovar os dentes", R.drawable.dentes));
        itemList.add(new Item("Ir para escola", R.drawable.escola));
        itemList.add(new Item("Tomar Banho", R.drawable.chuveiro));
        itemList.add(new Item("Jantar", R.drawable.janta));
        itemList.add(new Item("Lanche", R.drawable.lanche));
        itemList.add(new Item("Dever de casa", R.drawable.atividade));
        itemList.add(new Item( "Atividades", R.drawable.atividades));






        // Adicione mais itens conforme necessário

        // Configurar o adaptador
        ItemAdapter adapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(adapter);



        calendar = findViewById(R.id.calendarImage);
        rewards = findViewById(R.id.rewardsImage);
        home = findViewById(R.id.homeImage);
        profile = findViewById(R.id.profileImage);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
                finish();
                return;
            }
        });

    }
}