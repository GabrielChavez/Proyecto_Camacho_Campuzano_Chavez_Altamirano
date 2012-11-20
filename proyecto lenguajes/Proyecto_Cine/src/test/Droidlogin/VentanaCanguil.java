package test.Droidlogin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import test.Droidlogin.R;
import test.Droidlogin.Login.asynclogin;
import test.Droidlogin.library.AdminSQLiteOpenHelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.app.AlertDialog;

public class VentanaCanguil extends Activity{
	private int valor_canguil_grande=5,valor_canguil_mediano=3,valor_hot_pollo=3,valor_hot_res=4,valor_nachos=4,total=0;
	CheckBox check_canguil=null;
	CheckBox check_hot=null;
	CheckBox check_nachos=null;
	Button guardar_pedido=null;
	Button cancelar_pedido=null;
	RadioButton rb1=null;
	RadioButton rb2=null;
	RadioButton rb3=null;
	RadioButton rb4=null;
	private int total_categoria_canguil=0,total_categoria_hot=0,total_categoria_nacho=0;
	
	
	TextView canguil_grande=null,canguil_mediano=null,hot_pollo=null,hot_res=null,nacho=null,total_pedido=null;
	
	String logo_precio="$";
	String saldo,total_canguil,total_bebida,nombre,apellido,sala,funcion,asiento,arreglo_canguil="",arreglo_hot="",arreglo_nacho="",arreglo_total="",cedu,nombrecine;
	 
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_canguil);
        check_canguil=(CheckBox)findViewById(R.id.check_canguil);
        check_hot=(CheckBox)findViewById(R.id.check_hot);
        check_nachos=(CheckBox)findViewById(R.id.check_nachos);
        guardar_pedido=(Button)findViewById(R.id.boton_guardar_pedido);
        cancelar_pedido=(Button)findViewById(R.id.boton_cancelar_pedido);
        rb1=(RadioButton)findViewById(R.id.canguil_grande);
        rb2=(RadioButton)findViewById(R.id.canguil_mediano);
        rb3=(RadioButton)findViewById(R.id.hot_pollo);
        rb4=(RadioButton)findViewById(R.id.hot_res);
        canguil_grande=(TextView)findViewById(R.id.precio_grande_canguil);
        canguil_mediano=(TextView)findViewById(R.id.precio_mediano_canguil);
        hot_pollo=(TextView)findViewById(R.id.precio_pollo);
        hot_res=(TextView)findViewById(R.id.precio_res);
        nacho=(TextView)findViewById(R.id.precio_nachos);
        total_pedido=(TextView)findViewById(R.id.total_pedido_canguil);
        Bundle extras = getIntent().getExtras();
        if (extras != null) { 
      	   saldo=extras.getString("saldo");
      	 nombre=extras.getString("nombreuser");
      	apellido=extras.getString("apellido");
      	sala=extras.getString("sala");
      	asiento=extras.getString("asiento");
      	funcion=extras.getString("funcion");
      	 cedu=extras.getString("cedula");
      	 nombrecine=extras.getString("cine");
      	  //total_canguil=extras.getString("total_canguil");
      	  //total_bebida=extras.getString("total_bebida");
         }else{
      	   saldo="error";
      	   }
        
        canguil_grande.setText(logo_precio+String.valueOf(valor_canguil_grande));
        canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
        hot_pollo.setText(logo_precio+String.valueOf(valor_hot_pollo));
        hot_res.setText(logo_precio+String.valueOf(valor_hot_res));
        nacho.setText(logo_precio+String.valueOf(valor_nachos));
        total_pedido.setText(logo_precio+String.valueOf(total));
      
        
        rb1.setEnabled(false);
    	rb2.setEnabled(false);
    	rb3.setEnabled(false);
    	rb4.setEnabled(false);
    	total=total_categoria_canguil+total_categoria_nacho+total_categoria_hot;
        
        check_canguil.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		if(check_canguil.isChecked()==true){
                	rb1.setEnabled(true);
                	rb2.setEnabled(true);

               
        		}else{
        			total=0;
        			arreglo_canguil=" ";
        			total_categoria_canguil=0;
        			
        			total_pedido.setText(logo_precio+String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
        			rb1.setEnabled(false);
                	rb2.setEnabled(false);

        			
        		}
                
             
        		//verificamos si estan en blanco	
        		
        	}
        	});
check_nachos.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		if(check_nachos.isChecked()==true){
        			valor_nachos=4;
        			total_categoria_nacho=valor_nachos;
        			arreglo_nacho="nachos";
        			//total=total+valor_nachos;
        			nacho.setText(logo_precio+String.valueOf(valor_nachos));
        			 total_pedido.setText(logo_precio+String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
        		}else{
        			valor_nachos=0;
        			total_categoria_nacho=0;
        			arreglo_nacho=" ";
        			total=0;
        			nacho.setText(logo_precio+String.valueOf(valor_nachos));
        			 total_pedido.setText(logo_precio+String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));

        			
        		}
                
             
        		//verificamos si estan en blanco	
        		
        	}
        	});
        
        check_hot.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		if(check_hot.isChecked()==true){
           
                	rb3.setEnabled(true);
                	rb4.setEnabled(true);
                
        		}else{
        			total=0;
        			total_categoria_hot=0;
        			arreglo_hot=" ";
        			total_pedido.setText(logo_precio+String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
        			rb3.setEnabled(false);
                	rb4.setEnabled(false);
        			
        		}
        		
        	}
        	});
        rb1.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		if(rb1.isChecked()==true && rb2.isChecked()==false){
        		//	tmp_rb1=valor_canguil_grande;
        			total_categoria_canguil=valor_canguil_grande;
        			arreglo_canguil="";
        			arreglo_canguil="Canguil Grande";
        		//	total=total+valor_canguil_grande;
        		//canguil_grande.setText(logo_precio+String.valueOf(valor_canguil_grande));
        		 total_pedido.setText(logo_precio+String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
        		}
        		//else if(rb1.isChecked()==false && rb2.isChecked()==true){
        			//total=total-valor_canguil_grande;
        			
        			//total_pedido.setText(logo_precio+String.valueOf(total));
        	//	}
        		//verificamos si estan en blanco	
        		
        	}
        	});
        rb2.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		if(rb2.isChecked()==true && rb1.isChecked()==false){
        			total_categoria_canguil=valor_canguil_mediano;
        			arreglo_canguil="";
        			arreglo_canguil="Canguil Mediano";
        			//total=total+valor_canguil_mediano;
        		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
        		 total_pedido.setText(logo_precio+String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
        		 }
        		//else if(rb2.isChecked()==false && rb1.isChecked()==true){
        			// total=total-valor_canguil_mediano;
             		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
             		// total_pedido.setText(logo_precio+String.valueOf(total));
        			 
        			 
        		 //}
        		
        		//verificamos si estan en blanco	
        		
        	}
        	});
        rb3.setOnClickListener(new View.OnClickListener(){
    
	public void onClick(View vista){
		
		if(rb3.isChecked()==true && rb4.isChecked()==false){
			total_categoria_hot=valor_hot_pollo;
			arreglo_hot="";
			arreglo_hot="Hot Dog Pollo";
			//total=total+valor_hot_pollo;
		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
		 total_pedido.setText(logo_precio+String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
		 }
		//else{
			// total=total-valor_hot_pollo;
     		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
     		 //total_pedido.setText(logo_precio+String.valueOf(total));
			 
			 
		 //}
		//Extreamos datos de los EditText
	     // hot_pollo.setText(logo_precio+String.valueOf(valor_hot_pollo));
	      
		//verificamos si estan en blanco	
		
	}
	});
        rb4.setOnClickListener(new View.OnClickListener(){
    
	public void onClick(View vista){
		 
		if(rb4.isChecked()==true && rb3.isChecked()==false){
			total_categoria_hot=valor_hot_res;
			arreglo_hot="";
			arreglo_hot="Hot Dog Res";
		//	total=total+valor_hot_res;
		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
		 total_pedido.setText(logo_precio+String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
		 }
		//else{
			// total=total-valor_hot_res;
     		//canguil_mediano.setText(logo_precio+String.valueOf(valor_canguil_mediano));
     		// total_pedido.setText(logo_precio+String.valueOf(total));
			 
			 
		 //}
		//Extreamos datos de los EditText
		//hot_res.setText(logo_precio+String.valueOf(valor_hot_res));
		 
		//verificamos si estan en blanco	
		
	}
	});
        
       guardar_pedido.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		
        		 
        		 guardar(vista);
        		lanzar(vista);
        		
        	}
        	});
        
      cancelar_pedido.setOnClickListener(new View.OnClickListener(){
           
       	public void onClick(View vista){
       		 
       		//Extreamos datos de los EditText
       		arreglo_total="";
       		total=0;
       		finish(); 
       		//verificamos si estan en blanco	
       		
       	}
       	});
	}
	public void lanzar(View view) {
	       Intent i = new Intent(VentanaCanguil.this, MenuCompra.class );
	       i.putExtra("total_canguil",total);
	       i.putExtra("saldo", saldo);
	       i.putExtra("sala", sala);
	       i.putExtra("asiento", asiento);
	       i.putExtra("funcion", funcion);
	       i.putExtra("nombreuser", nombre);
	       i.putExtra("apellido", apellido);
	       i.putExtra("cedula", cedu);
	       i.putExtra("nombrecine", nombrecine);
	       startActivity(i);
	  }
     
	 public void guardar(View view) {
			 AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
	        SQLiteDatabase bd=admin.getWritableDatabase();
	        ContentValues registro=new ContentValues();
	        String dni="1";
	        registro.put("preciocanguil",String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho) );
	        registro.put("detallecanguil",arreglo_canguil );
	        registro.put("detallehot",arreglo_hot );
	        registro.put("detallenacho",arreglo_nacho );
	        int cant = bd.update("pedido", registro, "id="+dni, null);
	        bd.close();
	        Log.e("el valor total del canguil en ventana canguil",String.valueOf(total_categoria_canguil+total_categoria_hot+total_categoria_nacho));
	        Log.e("el string del canguil es guardado en la base",arreglo_total);
	        Log.e("canguil escogido",arreglo_canguil);
	        Log.e("hot dog escogido",arreglo_hot);
	        Log.e("nacho escogido",arreglo_nacho);
	        if (cant==1)
	            Log.e("EStado del valor canguil", String.valueOf(cant));
	        	        else
	        	Log.e("EStado del registro canguil", String.valueOf(cant));       
	        //Toast.makeText(this, "Se cargaron los datos de la persona", Toast.LENGTH_SHORT).show();     
	    }
	 
	 }
