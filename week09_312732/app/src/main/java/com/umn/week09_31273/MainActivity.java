package com.umn.week09_31273;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umn.week09_31273.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private MahasiswaViewModel mhsVM;
    private static final int REQUEST_TAMBAH = 1;
    private static final int REQUEST_EDIT = 2;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

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
                Intent addIntent = new Intent(MainActivity.this,
                        DetilActivity.class);
                startActivityForResult(addIntent,REQUEST_TAMBAH);
            }
        });
        rv = (RecyclerView) findViewById(R.id.rvMahasiswa);
        final MahasiswaListAdapter adapter = new MahasiswaListAdapter(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mhsVM = ViewModelProviders.of(this).get(MahasiswaViewModel.class);
        mhsVM.getDaftarMahasiswa().observe(this,
                new Observer<List<Mahasiswa>>() {
                    @Override
                    public void onChanged(List<Mahasiswa> mhss) {
                        adapter.setDaftarMahasiswa(mhss);
                    }
                });
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT |
                        ItemTouchHelper.RIGHT){
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder
                                                 viewHolder, int direction) {
                        int posisi = viewHolder.getAdapterPosition();

                        Mahasiswa mhs = adapter.getMahasiswaAtPosition(posisi);
                        if(direction == ItemTouchHelper.LEFT){
                            Toast.makeText(MainActivity.this,
                                    "Mahasiswa dengan NIM = "+mhs.getNim()+
                                            " dihapus",Toast.LENGTH_LONG).show();
                            mhsVM.delete(mhs);
                        }
                        else if (direction == ItemTouchHelper.RIGHT) {
                            Intent editIntet = new Intent (MainActivity.this,
                                    DetilActivity.class);
                            editIntet.putExtra("MAHASISWA", mhs);
                            startActivityForResult(editIntet,REQUEST_EDIT);
                        }
                    }
                }
        );
        helper.attachToRecyclerView(rv);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data){
        super.onActivityResult(reqCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Mahasiswa mhs = (Mahasiswa)
                    data.getSerializableExtra("MAHASISWA");
            if(reqCode == REQUEST_TAMBAH ) {
                mhsVM.insert(mhs);
            }
            else if (reqCode == REQUEST_EDIT) {
                mhsVM.update(mhs);
            }
        }
        rv.getAdapter().notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            mhsVM.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}