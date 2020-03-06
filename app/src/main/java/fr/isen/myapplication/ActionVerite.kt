package fr.isen.myapplication


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_action_verite.*
import com.google.firebase.database.DatabaseError
import kotlinx.android.synthetic.main.activity_jus_fruit.*
import kotlin.random.Random



class ActionVerite : AppCompatActivity() {


    private val TAG = "ActionVeriteActivity"
    private var userList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_verite)

        userList = intent.getSerializableExtra("user_list") as ArrayList<String>

        displayUser()

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Action")
        val actionList = arrayListOf<String>()
        val myRef2 = database.getReference("Verite")
        val veriteList = arrayListOf<String>()

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


        myRef2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    val value2 = it.getValue(String::class.java)
                    Log.d(TAG, "Value is: $value2")
                    veriteList.add(value2 ?: "")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        crow.setOnClickListener()
        {
            displayUser()
            questionDisplay.isVisible = false
        }
        ActionButton.setOnClickListener {
            questionDisplay.isVisible = true
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
            questionDisplay.isVisible = true
            if (veriteList.isNotEmpty()) {
                val randomIndexList2 = Random.nextInt(veriteList.size)
                val element2 = veriteList[randomIndexList2]
                questionDisplay.text = element2
            }
            else {
                Toast.makeText(this, "Erreur recuperation des données", Toast.LENGTH_LONG).show()
            }
        }



    }

    private fun displayUser(){
        val randomIndexList = Random.nextInt(userList.size)
        val element = userList[randomIndexList]
        AVuserName.text = "${element}" + ", à ton tour!"
    }

}
