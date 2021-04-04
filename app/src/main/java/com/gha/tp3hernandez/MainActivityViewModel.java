package com.gha.tp3hernandez;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<String> resultadoMutable;
    private final float DOLLAR_VALUE_IN_EUROS = .85f;
    private final float EURO_VALUE_IN_DOLLARS = 1.18f;

    public LiveData<String> getResultadoMutable() {
        if(resultadoMutable == null){
            resultadoMutable = new MutableLiveData<>();
        }
        return resultadoMutable;
    }

    public void calcular(String id, String cantidad){
        String mensaje = null;
        try{
            int idNumber = Integer.parseInt(id);
            float cantidadNumber = Float.parseFloat(cantidad);
            float resultado;
            if(idNumber == R.id.radioDolaresAEuros){
                resultado = cantidadNumber * DOLLAR_VALUE_IN_EUROS;
                mensaje = resultado + "";
            }else if(idNumber == R.id.radioEurosADolares){
                resultado = cantidadNumber * EURO_VALUE_IN_DOLLARS;
                mensaje = resultado + "";
            }
        }catch (NumberFormatException e) {
            mensaje = "Ocurrio un error con los datos introducidos.";
        }

        resultadoMutable.setValue(mensaje);
    }
}
