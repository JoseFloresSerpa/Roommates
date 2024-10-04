package com.example.roommates

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.roommates.DAO.AlquilerDAO
import com.example.roommates.DAO.UsuarioDAO

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        eliminartodo()
        eliminartodoUsuario()
        iniciar()
        iniciarUsuario()


    }

    fun IrLogin(view: View){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    private fun iniciar(){
        val dao = AlquilerDAO(baseContext)
        dao.insertar("Jr. Andahuaylas 256", "Cercado de Lima - Lima","3 dorm, 2 baños", 2, "S/.550",0,"https://i.postimg.cc/C1BfTvwF/1.png","Al frente de parque","jperez@gmail.com","+51 984572134")
        dao.insertar("Jr. León Velarde 3667", "Lince - Lima","4 dorm, 3 baños", 1, "S/.700",1,"https://i.postimg.cc/sfWZT0nm/2.png","Tiene sala común","galvarado@gmail.com","+51 97415423")
        dao.insertar("Av. Brasil 1569", "Jesus María - Lima","3 dorm, 3 baños", 3, "S/.850",0,"https://i.postimg.cc/sDRGq9qr/3.png","Baño dentro de dormitorio","hquispe@gmail.com","+51 91242346")
        dao.insertar("Av Tomas Marsano 5566", "Surquillo - Lima","3 dorm, 2 baños", 1, "S/.750",1,"https://i.postimg.cc/gj3XrnwQ/4.png","A 3 cuadras de supermercado","ygiraldo@gmail.com","+51 967124687")
        dao.insertar("Jr. Fray Luis de León 513", "San Borja - Lima","2 dorm, 1 baño", 2, "S/.850",0,"https://i.postimg.cc/76FJxgLS/5.png","Cuenta con muebles","umantilla@gmail.com","+51 978134569")
        dao.insertar("Los Claveles 1124", "El Agustino - Lima","4 dorm, 3 baños", 3, "S/.600",0,"https://i.postimg.cc/Przvjvn8/6.png","4to piso","ptubino@gmail.com","+51 934131357")
        dao.insertar("Av. de los Patriotas 6145", "San Miguel - Lima","2 dorm, 2 baños", 1, "S/.800",1,"https://i.postimg.cc/GmXBB02L/7.png","Cuenta con ascensor","tzapata@gmail.com","+51 964683468")
        dao.insertar("Av. San Juan 4131", "La Victoria - Lima","2 dorm, 1 baño", 1, "S/.550",0,"https://i.postimg.cc/Mp6MKZz3/8.png","Cerca de centro comercial","cpitana@gmail.com","+51 9456879714")
    }

    private fun iniciarUsuario(){
        val dao = UsuarioDAO(baseContext)
        dao.insertar("jvelarde@gmail.com", "Julio","Velarde", "+51997742435", "1234","")

    }

    private fun eliminartodoUsuario(){
        val dao = UsuarioDAO(baseContext)
        dao.eliminarTodos()
    }

    private fun eliminartodo(){
        val dao = AlquilerDAO(baseContext)
        dao.eliminarTodos()
    }


}