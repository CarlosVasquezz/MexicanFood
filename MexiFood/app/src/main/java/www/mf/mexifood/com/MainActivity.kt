package www.mf.mexifood.com

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonComidas: Button = findViewById(R.id.buttonComidas)
        val buttonBebidas: Button = findViewById(R.id.buttonBebidas)

        buttonComidas.setOnClickListener {
            val intent = Intent(this, ProductsActivity::class.java)
            startActivity(intent)
        }

        buttonBebidas.setOnClickListener {
            val intent = Intent(this, DrinksActivity::class.java)
            startActivity(intent)
        }
    }
}


