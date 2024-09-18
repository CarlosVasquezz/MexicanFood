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
import www.mf.mexifood.com.databinding.ActivityProductsBinding

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var productAdapter: ProductAdapter
    private lateinit var btnLogout: Button // Añadir variable para el botón de deslogeo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar View Binding
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()

        // Configurar RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(emptyList())
        binding.recyclerView.adapter = productAdapter

        // Cargar productos
        loadProductos()

        // Configurar botones
        binding.buttonBebidas.setOnClickListener {
            val intent = Intent(this, DrinksActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLogout.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        // Inicializar y configurar el botón de deslogeo
        btnLogout = binding.buttonLogout
        btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun loadProductos() {
        db.collection("comidas")
            .get()
            .addOnSuccessListener { result ->
                val productos = result.map { document ->
                    document.toObject(Producto::class.java)
                }
                productAdapter.updateData(productos)
            }
            .addOnFailureListener { exception ->
                Log.e("ProductsActivity", "Error al cargar productos", exception)
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