package fpoly.edu.grocerymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fpoly.edu.grocerymanager.dao.DemoDB;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tạo database
        DemoDB demoDB = new DemoDB(getApplicationContext());
    }
}