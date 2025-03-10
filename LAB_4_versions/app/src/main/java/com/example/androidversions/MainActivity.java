package com.example.androidversions;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button cupcake = findViewById(R.id.cupcake);
        cupcake.setOnClickListener((buttonView) -> showToast("cupcake"));
        Button donut = findViewById(R.id.donut);
        donut.setOnClickListener(v -> showToast("donut"));
        Button eclair = findViewById(R.id.eclair);
        eclair.setOnClickListener(v -> showToast("eclair"));
        Button froyo = findViewById(R.id.froyo);
        froyo.setOnClickListener(v -> showToast("froyo"));
        Button gingerbread = findViewById(R.id.gingerbread);
        gingerbread.setOnClickListener(v -> showToast("gingerbread"));
        Button honeycomb = findViewById(R.id.honeycomb);
        honeycomb.setOnClickListener(v -> showToast("honeycomb"));
        Button icecream = findViewById(R.id.icecream);
        icecream.setOnClickListener(v -> showToast("icecream"));
        Button jellybean = findViewById(R.id.jellybean);
        jellybean.setOnClickListener(v -> showToast("jellybean"));
        Button kitkat = findViewById(R.id.kitkat);
        kitkat.setOnClickListener(v -> showToast("kitkat"));
        Button lollipop = findViewById(R.id.lollipop);
        lollipop.setOnClickListener(v -> showToast("lollipop"));
        Button marshmallow = findViewById(R.id.marshmallow);
        marshmallow.setOnClickListener(v -> showToast("marshmallow"));
        Button nought = findViewById(R.id.nought);
        nought.setOnClickListener(v -> showToast("nought"));
        Button oreo = findViewById(R.id.oreo);
        oreo.setOnClickListener(v -> showToast("oreo"));
        Button pie = findViewById(R.id.pie);
        pie.setOnClickListener(v -> showToast("pie"));

    }

    private void showToast(String text) {
        Toast toast = new Toast(this);
        ImageView view = new ImageView(this);
        int res = getResources().getIdentifier(text, "drawable", this.getPackageName());
        view.setImageResource(res);
        toast.setView(view);
        toast.show();
    }
}