package fr.isen.myapplication


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_action_verite.*
import com.google.firebase.database.DatabaseError
import kotlin.random.Random


class ActionVerite : AppCompatActivity() {


    private val TAG = "ActionVeriteActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_verite)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Action")
        val actionList = arrayListOf<String>()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    val value = it.getValue(String::class.java)
                    Log.d(TAG, "Value is: $value")
                    actionList.add(value ?: "")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        ActionButton.setOnClickListener {
            Toast.makeText(this , "Vous avez choisi action", Toast.LENGTH_LONG).show()
            if (actionList.isNotEmpty()) {
                val randomIndexList = Random.nextInt(actionList.size)
                val element = actionList[randomIndexList]
                questionDisplay.text = element
            }
            else {
                Toast.makeText(this, "Erreur recuperation des données", Toast.LENGTH_LONG).show()
            }

        }

        VeriteButton.setOnClickListener {
            Toast.makeText(this , "Vous avez choisi verite", Toast.LENGTH_LONG).show();

        }

    }
}
