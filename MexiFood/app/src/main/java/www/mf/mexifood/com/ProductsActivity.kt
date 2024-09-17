package www.mf.mexifood.com

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import www.mf.mexifood.com.databinding.ActivityProductsBinding

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var productAdapter: ProductAdapter

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

        loadProductos()
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
}
