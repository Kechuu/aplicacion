package com.cabrerajesusk.miaplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContactanosActivity extends AppCompatActivity {

    private Button atras;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos);
        atras = (Button) findViewById(R.id.idAtrasContactanos);

        atras.setOnClickListener(v -> startActivity(new Intent(ContactanosActivity.this,LoginActivity.class)));
    }
}
