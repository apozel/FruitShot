package fr.isen.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_jus_fruit.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt
import kotlin.random.Random

class JusFruitActivity : AppCompatActivity() {

    private val TAG = "ActionVeriteActivity"
    private var timer: CountDownTimer? = null
    private val questionList = arrayListOf<String>()

    private var userList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jus_fruit)
        questionTextView.isVisible = true
        progressBar.isVisible = true
        timerView.isVisible = false


        userList = intent.getSerializableExtra("user_list") as ArrayList<String>
        //Log.d("coucou", userList[0])
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("questions")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    val value = it.getValue(String::class.java)
                    Log.d(TAG, "Value is: $value")
                    questionList.add(value ?: "")
                }
                displayRandomQuestion()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        okButton.setOnClickListener {
            if (questionList.isNotEmpty()) {
                displayRandomQuestion()
                okButton.isVisible = false
                nokButton.isVisible = false
            } else {
                Toast.makeText(this, "Erreur recuperation des données", Toast.LENGTH_LONG).show()
            }
        }

        nokButton.setOnClickListener{
            val intent = Intent(this, ChoixJeuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }


    }


    private fun displayRandomQuestion() {
        questionTextView.isVisible = true
        val randomIndexList = Random.nextInt(questionList.size)
        val element = questionList[randomIndexList]
        questionTextView.text = element
        displayUser()
        progressBar.isVisible = false
        startTimer()
    }

    private fun displayUser(){
        val randomIndexList = Random.nextInt(userList.size)
        val element = userList[randomIndexList]
        userName.text = "${element}" + ", à ton tour!"
    }


    private fun startTimer() {
        timerView.isVisible = true
        timer = object : CountDownTimer(2500, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondLeft  = (millisUntilFinished.toFloat()/1000).roundToInt()
                timerView.text = secondLeft.toString()
            }

            override fun onFinish() {
                //TODO afficher les boutons OK/NOK et afficher une autre question
                okButton.isVisible = true
                nokButton.isVisible = true
                timerView.isVisible = false
                questionTextView.isVisible = false
                okButton.isVisible = true

            }
        }
        timer?.start()
    }
}
