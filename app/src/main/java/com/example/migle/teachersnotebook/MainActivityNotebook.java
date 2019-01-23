package com.example.migle.teachersnotebook;

import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class MainActivityNotebook extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    MaterialSearchView saerchView;
    EditText editTitleCourse;
    DataHelper myDatabase;
    Button btnAddCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notebook);

        myDatabase = new DataHelper(this);

       // editTitleCourse = findViewById(R.id.editCourse_title);
        btnAddCourse = findViewById(R.id.btn_add_course);
        addDataCourse();
        viewAllCourses();
        updateDataCourse();

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        saerchView = findViewById(R.id.search_view);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        //toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item){

                            switch(item.getItemId()){
                        case R.id.nav_calendar:
                            item.setChecked(true);
                            displayMessage("Import Selected...");
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.nav_documents:
                            item.setChecked(true);
                            displayMessage("Import Selected...");
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.nav_mon:
                            item.setChecked(true);
                            displayMessage("Import Selected...");
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.nav_help:
                            item.setChecked(true);
                            displayMessage("Import Selected...");
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

        public void addDataCourse(){
            btnAddCourse.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean isInserted = myDatabase.insertData(editTitleCourse.getText().toString());
                            if(isInserted == true)
                                Toast.makeText(MainActivityNotebook.this, "Данните са въведени", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivityNotebook.this, "Данните не са въведени", Toast.LENGTH_LONG).show();

                    }
                }
            );
        }
    public void viewAllCourses(){
        Cursor result = myDatabase.getCourseData();
        if(result.getCount() == 0){
            //show message
            showMessage("Грешка!", "Няма намерени резултати!");
            return;
        }
        StringBuffer buffer= new StringBuffer();
        while (result.moveToNext()){
            buffer.append("Id:" + result.getString(0) + "\n");
            buffer.append("Course_title:" + result.getString(1) + "\n");
        }

    }

    public  void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public  void  updateDataCourse(){
        //https://www.youtube.com/watch?v=PA4A9IesyCg
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
