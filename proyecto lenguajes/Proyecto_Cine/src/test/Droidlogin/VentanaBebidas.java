package test.Droidlogin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import test.Droidlogin.R;
import test.Droidlogin.Login.asynclogin;
import test.Droidlogin.library.AdminSQLiteOpenHelper;

import android.widget.AdapterView.OnItemSelectedListener;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class VentanaBebidas extends Activity {
	
	private int bebida_grande_valor=5,bebida_mediano_valor=3,total=0;
	CheckBox check_bebida=null;
	Button guardar_pedido=null;
	Button cancelar_pedido=null;
	RadioButton rb1=null;
	RadioButton rb2=null;
	
	
	TextView bebida_grande=null,bebida_mediano=null,total_texto=null;
	Spinner s ;
	
	String logo_precio="$";
	String saldo,total_canguil,total_bebida,nombre,apellido,sala,funcion,asiento,arreglo_bebida="",cola_escogida=" ",string_cola_escogida,cedu,nombrecine;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_bebidas);
        ArrayAdapter <CharSequence> adapter =new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("Coca Cola");
        adapter.add("Sprite");
        adapter.add("Pepsi");
        s = (Spinner) findViewById(R.id.spinner1);
        s.setAdapter(adapter);
        check_bebida=(CheckBox)findViewById(R.id.checkbebida);
        guardar_pedido=(Button)findViewById(R.id.boton_guardar_bebida);
        cancelar_pedido=(Button)findViewById(R.id.boton_cancelar_bebida);
        rb1=(RadioButton)findViewById(R.id.radio0);
        rb2=(RadioButton)findViewById(R.id.radio1);
        bebida_grande=(TextView)findViewById(R.id.valor_bebida_grande);
        bebida_mediano=(TextView)findViewById(R.id.valor_bebida_mediano);
        total_texto=(TextView)findViewById(R.id.total_valor_bebida);
     
        
      Bundle extras = getIntent().getExtras();
      if (extras != null) { 
     	   saldo=extras.getString("saldo");
     	  total_canguil=extras.getString("total_canguil");
     	  total_bebida=extras.getString("total_bebida");
     	 nombre=extras.getString("nombreuser");
       	apellido=extras.getString("apellido");
       	sala=extras.getString("sala");
       	asiento=extras.getString("asiento");
       	funcion=extras.getString("funcion");
       	cedu=extras.getString("cedula");
     	 nombrecine=extras.getString("cine");
        }else{
     	   saldo="error";
     	   }
       
        
        bebida_grande.setText(logo_precio+String.valueOf(bebida_grande_valor));
        bebida_mediano.setText(logo_precio+String.valueOf(bebida_mediano_valor));
        total_texto.setText(logo_precio+String.valueOf(total));
        
        rb1.setEnabled(false);
    	rb2.setEnabled(false);
    	s.setEnabled(false);
    
        
    	s.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				cola_escogida=arg0.getItemAtPosition(arg2).toString();
								
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
    		
    		
    		
    	});
        check_bebida.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		if(check_bebida.isChecked()==true){
                	rb1.setEnabled(true);
                	rb2.setEnabled(true);
                	s.setEnabled(true);
                	

    
        		}else{
        			total=0;
        			arreglo_bebida=" ";
        			total_texto.setText(logo_precio+String.valueOf(total));
        			
        			rb1.setEnabled(false);
                	rb2.setEnabled(false);

        			
        		}
                
             
        		//verificamos si estan en blanco	
        		
        	}
        	});
        
        rb1.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		if(rb1.isChecked()==true && rb2.isChecked()==false){
        			total=bebida_grande_valor;
        			arreglo_bebida="";
        			arreglo_bebida=(String) rb1.getText();
            		//canguil_grande.setText(logo_precio+String.valueOf(valor_canguil_grande));
            		 total_texto.setText(logo_precio+String.valueOf(total));
        			
        		}
        		//verificamos si estan en blanco	
        		
        	}
        	});
        rb2.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		if(rb2.isChecked()==true && rb1.isChecked()==false){
        			total=bebida_mediano_valor;
        			arreglo_bebida="";
        			arreglo_bebida=(String)rb2.getText();
            		//canguil_grande.setText(logo_precio+String.valueOf(valor_canguil_grande));
            		 total_texto.setText(logo_precio+String.valueOf(total));
        			
        		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
        		 
        		 }
        		
        		//verificamos si estan en blanco	
        		
        	}
        	});
    
    
        
       guardar_pedido.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		guardar(vista);
        		
        		lanzar(vista);
        		//verificamos si estan en blanco	
        		
        	}
        	});
        
      cancelar_pedido.setOnClickListener(new View.OnClickListener(){
           
       	public void onClick(View vista){
       		 
       		//Extreamos datos de los EditText
       		finish(); 
       		//verificamos si estan en blanco	
       		
       	}
       	});
	}
	public void lanzar(View view) {
	       Intent i = new Intent(VentanaBebidas.this, MenuCompra.class );
	      i.putExtra("total_bebida",total);
	      i.putExtra("saldo",saldo);
	      i.putExtra("sala", sala);
	       i.putExtra("asiento", asiento);
	       i.putExtra("funcion", funcion);
	       i.putExtra("nombreuser", nombre);
	       i.putExtra("apellido", apellido);
	       i.putExtra("cedula", cedu);
	       i.putExtra("nombrecine", nombrecine);
	       startActivity(i);
	  }
	public void guardar(View v) {
		AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        String dni="1";
        registro.put("preciobebida",String.valueOf(total) );
        registro.put("detallebebida",cola_escogida + arreglo_bebida);
        int cant = bd.update("pedido", registro, "id="+dni, null);
        bd.close();
        Log.e("el valor total de bebida en ventana bebida",String.valueOf(total));
        Log.e("el string del bebida en ventana bebida",cola_escogida + arreglo_bebida);
        Log.e("cola escogida",cola_escogida);
        Log.e("tamaño escogido",arreglo_bebida);
        
        if (cant==1){
            Log.e("EStado del valor bebida", String.valueOf(cant));
        
        }
        else
        	Log.e("EStado del registro bebida", String.valueOf(cant));       
        //Toast.makeText(this, "Se cargaron los datos de la persona", Toast.LENGTH_SHORT).show();        
    }

	
}
