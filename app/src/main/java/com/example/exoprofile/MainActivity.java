package com.example.exoprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvEXO;
    private ArrayList<EXO> list = new ArrayList<>();
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvEXO = findViewById(R.id.rv_exo);
        rvEXO.setHasFixedSize(true);

        list.addAll(getListEXO());
        showRecyclerCardView();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()){
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.nav_album:
                        selectedFragment = new AlbumFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, selectedFragment).commit();
                return true;
            }
        });
    }

    public ArrayList<EXO> getListEXO(){
        String[] dataName = getResources().getStringArray(R.array.data_name);
        String[] dataDescription = getResources().getStringArray(R.array.data_description);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
        ArrayList<EXO> listEXO = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++){
            EXO exo = new EXO();
            exo.setName(dataName[i]);
            exo.setDescription(dataDescription[i]);
            exo.setPhoto(dataPhoto.getResourceId(i,-1));
            listEXO.add(exo);
        }
        return listEXO;
    }

    private void showRecyclerCardView() {
        rvEXO.setLayoutManager(new LinearLayoutManager(this));
        ListEXOAdapter listEXOAdapter = new ListEXOAdapter(list);
        rvEXO.setAdapter(listEXOAdapter);

        listEXOAdapter.setOnItemClickCallback(new ListEXOAdapter.OnItemClickCallback(){
            @Override
            public void onItemClicked(EXO exo) {
                Intent move = new Intent(MainActivity.this, DetailActivity.class);
                move.putExtra(DetailActivity.ITEM_EXTRA, exo);
                startActivity(move);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView)
                    (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                    searchView.clearFocus();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MenuFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.menu1) {
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
            return true;
        } else {
            return true;
        }
    }

}