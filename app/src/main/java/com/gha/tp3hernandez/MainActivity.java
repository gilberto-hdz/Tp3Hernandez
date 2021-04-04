package com.gha.tp3hernandez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private Button btnConvertir;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText etDolares, etEuros, etCambio;
    private MainActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        InicializarComponentes();
        checkButton(radioButton);

        vm.getResultadoMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etCambio.setText(s);
            }
        });
    }

    private void InicializarComponentes(){
        btnConvertir = findViewById(R.id.btnCovertir);
        radioGroup = findViewById(R.id.radioGroup);
        etDolares = findViewById(R.id.etDolares);
        etEuros = findViewById(R.id.etEuros);
        etCambio = findViewById(R.id.etCambio);
        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButton.getId() == R.id.radioDolaresAEuros){
                    vm.calcular(radioButton.getId() + "", etDolares.getText().toString());
                }else if(radioButton.getId() == R.id.radioEurosADolares){
                    vm.calcular(radioButton.getId() + "", etEuros.getText().toString());
                }
            }
        });
    }


    public void checkButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        if(radioId == R.id.radioDolaresAEuros){
            etEuros.setEnabled(false);
            etDolares.setEnabled(true);
        }else if(radioId == R.id.radioEurosADolares){
            etEuros.setEnabled(true);
            etDolares.setEnabled(false);
        }
    }
}