package www.mf.mexifood.com

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import www.mf.mexifood.com.databinding.ActivityDrinksBinding

class DrinksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrinksBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var drinkAdapter: DrinkAdapter

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

        // Configurar botón para ver comidas
        binding.buttonComidas.setOnClickListener {
            val intent = Intent(this, ProductsActivity::class.java)
            startActivity(intent)
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
}
