package com.cabrerajesusk.miaplicacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ContactanosActivity extends Fragment {

    private Button atras;
    public ContactanosActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_contactanos, container, false);
    }

    @Override
    public void onViewCreated(View view , Bundle saveInstanceState){
        super.onViewCreated(view,saveInstanceState);

        atras = (Button) view.findViewById(R.id.idAtrasContactanos);

        //Esto se usara para navegar desde la ventana actual a las demas
        final NavController navController = Navigation.findNavController(view);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.loginActivity);
            }
        });
    }
}
