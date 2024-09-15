package www.mf.mexifood.com

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LogOut : AppCompatActivity() {

        private lateinit var auth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            auth = FirebaseAuth.getInstance()

            val btnLogout = findViewById<Button>(R.id.btnLogout)
            btnLogout.setOnClickListener {
                Log.d("MainActivity", "Logout button clicked")
                logout()
            }
        }

        private fun logout() {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


