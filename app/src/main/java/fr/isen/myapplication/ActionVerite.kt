package fr.isen.myapplication


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_action_verite.*

class ActionVerite : AppCompatActivity() {

   /* val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("message")

    myRef.setValue("Hello, World!")*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_verite)


        ActionButton.setOnClickListener {
            Toast.makeText(this , "Vous avez choisi action", Toast.LENGTH_LONG).show();


        }

        VeriteButton.setOnClickListener {
            Toast.makeText(this , "Vous avez choisi verite", Toast.LENGTH_LONG).show();

        }

    }
}
