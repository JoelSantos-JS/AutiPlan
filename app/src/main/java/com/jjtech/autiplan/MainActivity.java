package com.jjtech.autiplan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private Map<String, List<Item>> periodTasks;
    private String currentPeriod = "morning";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar a Toolbar
        setupToolbar();

        // Inicializar o RecyclerView
        setupRecyclerView();

        // Inicializar dados antes de configurar os chips
        initializeData();

        // Configurar os chips de período
        setupPeriodChips();

        // Configurar navegação inferior
        setupBottomNavigation();
    }


    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView menuIcon = findViewById(R.id.menuIcon);
        ImageView notificationIcon = findViewById(R.id.notificationIcon);
        ImageView settingsIcon = findViewById(R.id.settingsIcon);

        menuIcon.setOnClickListener(v -> {
            // Implementar menu lateral aqui
        });

        notificationIcon.setOnClickListener(v -> {
            // Implementar tela de notificações aqui
        });

        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Profile.class);
            startActivity(intent);
            finish();
            return;
        });
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1)); // Mudado para lista única
        adapter = new ItemAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    private void setupPeriodChips() {
        ChipGroup chipGroup = findViewById(R.id.timeChipGroup);

        Map<Integer, String> periodMap = new HashMap<>();
        periodMap.put(R.id.morningChip, "morning");
        periodMap.put(R.id.afternoonChip, "afternoon");
        periodMap.put(R.id.eveningChip, "evening");

        chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String period = periodMap.get(checkedId);
            if (period != null) {
                updateTasksList(period);
            }
        });

        // Selecionar chip da manhã por padrão
        chipGroup.check(R.id.morningChip);
    }

    private void initializeData() {
        periodTasks = new HashMap<>();

        // Tarefas da manhã
        List<Item> morningTasks = new ArrayList<>();
        morningTasks.add(new Item("Café da manhã", R.drawable.cafe));
        morningTasks.add(new Item("Escovar os dentes", R.drawable.dentes));
        morningTasks.add(new Item("Ir para escola", R.drawable.escola));
        periodTasks.put("morning", morningTasks);

        // Tarefas da tarde
        List<Item> afternoonTasks = new ArrayList<>();
        afternoonTasks.add(new Item("Almoço", R.drawable.janta));
        afternoonTasks.add(new Item("Atividades", R.drawable.atividades));
        afternoonTasks.add(new Item("Lanche", R.drawable.lanche));
        periodTasks.put("afternoon", afternoonTasks);

        // Tarefas da noite
        List<Item> eveningTasks = new ArrayList<>();
        eveningTasks.add(new Item("Jantar", R.drawable.janta));
        eveningTasks.add(new Item("Tomar Banho", R.drawable.chuveiro));
        eveningTasks.add(new Item("Dever de casa", R.drawable.atividade));
        periodTasks.put("evening", eveningTasks);

        // Mostrar tarefas da manhã inicialmente
        updateTasksList("morning");
    }

    private void updateTasksList(String period) {
        currentPeriod = period;
        List<Item> tasks = periodTasks.get(period);
        adapter.updateItems(tasks);
    }

    private void setupBottomNavigation() {
        View homeNav = findViewById(R.id.nav_home);
        View rewardsNav = findViewById(R.id.nav_rewards);
        View settingsNav = findViewById(R.id.nav_settings);

        homeNav.setOnClickListener(v -> {
            // Já estamos na tela inicial
        });

        rewardsNav.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RewardsActivity.class);
            startActivity(intent);
        });

        settingsNav.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Profile.class);
            startActivity(intent);
        });
    }
}