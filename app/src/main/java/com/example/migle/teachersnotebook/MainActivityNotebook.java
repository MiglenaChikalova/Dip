package com.example.migle.teachersnotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class MainActivityNotebook extends AppCompatActivity {
    public Toolbar toolbar;
    private DrawerLayout drawerLayout;
    public NavigationView navigationView;
    MaterialSearchView saerchView;
    //DataHelper myDatabase;
    Button btnAddCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notebook);

        //myDatabase = new DataHelper(this);

       // editTitleCourse = findViewById(R.id.editCourse_title);
        btnAddCourse = findViewById(R.id.btn_add_course);


        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        saerchView = findViewById(R.id.search_view);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item){

                            switch(item.getItemId()){
                        case R.id.nav_calendar:
                            item.setChecked(true);
                            displayMessage("Calendar...");
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.nav_documents:
                            item.setChecked(true);
                            displayMessage("Documents...");
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.nav_mon:
                            item.setChecked(true);
                            displayMessage("Mon...");
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.nav_help:
                            item.setChecked(true);
                            displayMessage("Help...");
                            drawerLayout.closeDrawers();
                            return true;
                    }
                            return false;
                }
            });
        }
        private void displayMessage(String message){
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        saerchView.setMenuItem(item);
        return true;
    }
}
