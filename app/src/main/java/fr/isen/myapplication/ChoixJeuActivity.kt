package fr.isen.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.myapplication.pmu.PmuActivity
import kotlinx.android.synthetic.main.activity_choix_jeu.*
import kotlinx.android.synthetic.main.activity_main.*

class ChoixJeuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_jeu)

        buttonGame1.setOnClickListener {
            val intent = Intent(this, PmuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        buttonGame2.setOnClickListener {
            val intent = Intent(this, PurpleActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        buttonGame3.setOnClickListener {
            val userList = intent.getSerializableExtra("user_list")
            val intentD = Intent(this, ActionVerite::class.java)
            intentD.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            intentD.putExtra("user_list",  userList)
            startActivity(intentD)

        }

        buttonGame4.setOnClickListener {
            val userList = intent.getSerializableExtra("user_list")
            val intentD = Intent(this, JusFruitActivity::class.java)
            intentD.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            intentD.putExtra("user_list",  userList)
            startActivity(intentD)

        }

    }

    
}
