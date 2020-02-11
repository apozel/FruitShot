package fr.isen.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_main.*
import fr.isen.myapplication.pmu.PmuActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userList = arrayListOf<String>()
        playButton.setOnClickListener {
                userList.clear()
            userLayout.children.forEach {
                val editText = it as EditText
                userList.add(editText.text.toString())
            }
            val intent = Intent(this, ChoixJeuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        }

        userList.add("")
        addButton.setOnClickListener{
            if (userList.size <= 5) {
                userList.add("")
                val newUser = EditText(this)
                newUser.hint = "Joueur ${userList.size}"
                userLayout.addView(newUser)
            }
        }
    }
}
