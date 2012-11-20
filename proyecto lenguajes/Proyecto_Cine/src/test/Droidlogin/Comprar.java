package test.Droidlogin;



import java.util.ArrayList;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.app.Activity;
import android.content.Intent;

public class Comprar extends Activity {
	private RadioButton rb1, rb2, rb3;
	public LinearLayout ll;
	public EditText et;
	private String usuario,cedu,saldo,nombre_cine,apellido,nombre;
	
		
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comprar);
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null) {
     	   
     	   usuario=extras.getString("usuario");
     	  nombre=extras.getString("nombre");
     	   cedu=extras.getString("cedula");
     	   saldo=extras.getString("saldo");
     	  apellido=extras.getString("apellido");
     	   
        }else{
     	   usuario="error";
     	   }
        
        //lv.
             
       rb1 = (RadioButton)findViewById(R.id.radioButton1);
        rb2 = (RadioButton)findViewById(R.id.radioButton2);
        rb3 = (RadioButton)findViewById(R.id.radioButton3);

       

       
        rb1.setOnClickListener(new RadioButton.OnClickListener(){
            public void onClick(View v) {
                
            	nombre_cine=(String)rb1.getText();
            	lanzar(v);
            }
        });
        rb2.setOnClickListener(new RadioButton.OnClickListener(){
            public void onClick(View v) {
            	nombre_cine=(String)rb2.getText();
            	lanzar(v);
            }
        });
        
        rb3.setOnClickListener(new RadioButton.OnClickListener(){
           
            public void onClick(View v) {
            	nombre_cine=(String)rb3.getText();
            	lanzar(v);
            }
        });
       
        
    }
	public void cerrar(View view) {
    	finish();
    }
	
	public void lanzar(View view) {
        Intent i = new Intent(Comprar.this, FacturaCodigo.class );
        i.putExtra("cine", nombre_cine);
        i.putExtra("usuario", usuario);
        i.putExtra("cedula", cedu);
        i.putExtra("saldo", saldo);
        i.putExtra("nombre", nombre);
        i.putExtra("apellido", apellido);
        startActivity(i);
  }

}
