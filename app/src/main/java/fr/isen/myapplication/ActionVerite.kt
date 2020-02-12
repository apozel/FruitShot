package fr.isen.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_action_verite.*
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.storage.FirebaseStorage

class ActionVerite : AppCompatActivity() {

   // val storage = FirebaseStorage.getInstance()
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
