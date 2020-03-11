package fr.isen.myapplication

import android.content.Context
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
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class JusFruitActivity : AppCompatActivity(), SensorEventListener {

    private val TAG = "ActionVeriteActivity"
    private var timer: CountDownTimer? = null
    private val questionList = arrayListOf<String>()
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    private var allowChange = false
    private var userList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jus_fruit)
        questionTextView.isVisible = true
        progressBar.isVisible = true
        timerView.isVisible = false
        endText.isVisible= false
        // Sensor
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        // focus in accelerometer
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)


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


        nokButton.setOnClickListener {
            val intent = Intent(this, ChoixJeuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("user_list", userList)
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

    private fun displayUser() {
        val randomIndexList = Random.nextInt(userList.size)
        val element = userList[randomIndexList]
        userName.text = "${element}" + ", à ton tour!"
    }


    private fun startTimer() {
        timerView.isVisible = true
        timer = object : CountDownTimer(8500, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondLeft = (millisUntilFinished.toFloat() / 1000).roundToInt()
                timerView.text = secondLeft.toString()
            }

            override fun onFinish() {
                nokButton.isVisible = true
                timerView.isVisible = false
                questionTextView.isVisible = false
                endText.isVisible = true
                allowChange=true

            }
        }
        timer?.start()
    }


    //quatre fonctions du sensor

    override fun onResume() {
        super.onResume()
        accelerometer?.also { it ->
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }



    override fun onSensorChanged(event: SensorEvent?) {

        if (event!!.values[0] + event.values[1] + event.values[2] >= 20 && allowChange) {

            if (questionList.isNotEmpty()) {
                displayRandomQuestion()
                nokButton.isVisible = false
                endText.isVisible= false
            } else {
                Toast.makeText(this, "Erreur recuperation des données", Toast.LENGTH_LONG).show()
            }
            allowChange = false
        }
    }

}
