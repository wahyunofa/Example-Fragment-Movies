package com.nopex77.movies;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.nopex77.movies.adapter.CardviewFilmAdapter_class;
import com.nopex77.movies.adapter.GridFilmAdapter_class;
import com.nopex77.movies.adapter.ListFilmAdapter_class;
import com.nopex77.movies.film.FilmData_class;
import com.nopex77.movies.film.Film_class;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvFilm;
    private ArrayList<Film_class> list = new ArrayList<>();
    private String title = "Mode List";

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        setActionBarTitle(title);

        rvFilm = findViewById(R.id.rv_film);
        rvFilm.setHasFixedSize(true);

        list.addAll(FilmData_class.getListData());
        showRecyclerList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void showRecyclerList() {
        rvFilm.setLayoutManager(new LinearLayoutManager(this));
        ListFilmAdapter_class listFilmAdapter_class = new ListFilmAdapter_class(list);
        rvFilm.setAdapter(listFilmAdapter_class);
        listFilmAdapter_class.setOnItemClickCallback(new ListFilmAdapter_class.OnItemClickCallback() {
            @Override
            public void onItemClicked(Film_class data) {
                showSelectedHero(data);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list:
                title = "Mode List";
                showRecyclerList();
                break;

            case R.id.action_grid:
                title = "Mode Grid";
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                title = "Mode CardView";
                showRecyclerCardView();
                break;
        }
        setActionBarTitle(title);
    }

    private void showRecyclerGrid() {
        rvFilm.setLayoutManager(new GridLayoutManager(this, 2));
        GridFilmAdapter_class gridFilmAdapter = new GridFilmAdapter_class(list);
        rvFilm.setAdapter(gridFilmAdapter);
        gridFilmAdapter.setOnItemClickCallback(new GridFilmAdapter_class.OnItemClickCallback() {
            @Override
            public void onItemClicked(Film_class data) {
                showSelectedHero(data);
            }
        });

    }

    private void showRecyclerCardView(){
        rvFilm.setLayoutManager(new LinearLayoutManager(this));
        CardviewFilmAdapter_class cardViewHeroAdapter = new CardviewFilmAdapter_class(list);
        rvFilm.setAdapter(cardViewHeroAdapter);
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void showSelectedHero(Film_class film) {
        Toast.makeText(this, "Kamu memilih " + film.getNama(), Toast.LENGTH_SHORT).show();
    }
}
