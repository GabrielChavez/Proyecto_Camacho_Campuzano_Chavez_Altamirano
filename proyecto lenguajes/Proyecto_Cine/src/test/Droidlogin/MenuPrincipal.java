package test.Droidlogin;

import test.Droidlogin.R;
import test.Droidlogin.Login.asynclogin;
import test.Droidlogin.library.AdminSQLiteOpenHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MenuPrincipal extends Activity{
	Button botonconsultasaldo=null;
	Button botoncomprar=null;
	Button botonsalir=null;
	Button botonmapas=null;
	String user,saldo,apellido;
	TextView txt_usr;
	String bienvenida="Hola ";
	private String usuario,cedu;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuprincipal);
        botonconsultasaldo=(Button)findViewById(R.id.SaldoDisponible);
        botoncomprar=(Button)findViewById(R.id.Comprar);
        botonsalir=(Button)findViewById(R.id.salir);
        botonmapas=(Button)findViewById(R.id.cines);
        txt_usr= (TextView) findViewById(R.id.nombre_usuario);
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null) {
     	   user  = extras.getString("user");//usuario
     	   saldo =extras.getString("saldo");
     	   usuario=extras.getString("usuario");
     	  apellido=extras.getString("apellido");
     	   cedu=extras.getString("cedula");
        }else{
     	   user="error";
     	   }
        
        txt_usr.setText(bienvenida+user+"!!!");
        
        botonconsultasaldo.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		lanzar(vista);
        		//verificamos si estan en blanco	
        		
        	}
        	});
        
       botoncomprar.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View vista){
        		 
        		//Extreamos datos de los EditText
        		
        		lanzar_compra(vista);
        		//verificamos si estan en blanco	
        		
        	}
        	});
       
       botonmapas.setOnClickListener(new View.OnClickListener(){
           
       	public void onClick(View vista){
       		 
       		//Extreamos datos de los EditText
       		
       		lanzar_mapa(vista);
       		//verificamos si estan en blanco	
       		
       	}
       	});

      
       botonsalir.setOnClickListener(new View.OnClickListener(){
           
       	public void onClick(View vista){
       		 
       		//Extreamos datos de los EditText
       		eliminarbase();
       		finish(); 
       		//verificamos si estan en blanco	
       		
       	}
       	});
	}
     
	public void eliminarbase(){
    	AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase(); 
        admin.onDelete(bd);
        Log.e("estado de base", "eliminada");
    	
    }
	
	public void lanzar(View view) {
       Intent i = new Intent(MenuPrincipal.this, SaldoDisponible.class );
       i.putExtra("saldo",saldo);
       startActivity(i);
  }
	public void lanzar_compra(View view) {
       Intent j = new Intent(MenuPrincipal.this, Comprar.class );
       j.putExtra("usuario",usuario);
       j.putExtra("cedula",cedu);
       j.putExtra("saldo",saldo);
       j.putExtra("nombre",user);
       j.putExtra("apellido",apellido);
        startActivity(j);
  }
	public void lanzar_mapa(View view) {
	       Intent m = new Intent(MenuPrincipal.this, Mapas.class );
	       m.putExtra("usuario",usuario);
	       m.putExtra("cedula",cedu);
	        startActivity(m);
	  }
	 public boolean onKeyDown(int keyCode, KeyEvent event)  {
	     if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	         // no hacemos nada.
	         return true;
	     }

	     return super.onKeyDown(keyCode, event);
	 }
}
