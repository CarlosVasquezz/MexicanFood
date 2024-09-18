package www.mf.mexifood.com

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import www.mf.mexifood.com.databinding.ActivityDrinksBinding

class DrinksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrinksBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var drinkAdapter: DrinkAdapter
    private lateinit var btnLogout: Button // Variable para el botón de deslogeo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar View Binding
        binding = ActivityDrinksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()

        // Configurar RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        drinkAdapter = DrinkAdapter(emptyList())
        binding.recyclerView.adapter = drinkAdapter

        // Cargar bebidas
        loadBebidas()

        // Configurar botones
        binding.buttonComidas.setOnClickListener {
            val intent = Intent(this, ProductsActivity::class.java)
            startActivity(intent)
        }

        binding.buttonCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        // Inicializar y configurar el botón de deslogeo
        btnLogout = binding.buttonLogout
        btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun loadBebidas() {
        db.collection("bebidas")
            .get()
            .addOnSuccessListener { result ->
                val bebidas = result.map { document ->
                    document.toObject(Drink::class.java)
                }
                drinkAdapter.updateData(bebidas)
            }
            .addOnFailureListener { exception ->
                Log.e("DrinksActivity", "Error al cargar bebidas", exception)
            }
    }

    private fun logout() {
        val auth = FirebaseAuth.getInstance()
        auth.signOut() // Deslogear al usuario
        Toast.makeText(applicationContext, "Deslogueado exitosamente", Toast.LENGTH_SHORT).show()
        // Regresar a la pantalla de login
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}