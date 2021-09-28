package ec.grouptwo.androidappproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ec.grouptwo.androidappproject.SQLite.DatabaseHandler
import ec.grouptwo.androidappproject.SQLite.Query
import ec.grouptwo.androidappproject.user.User


class MainActivity : AppCompatActivity() {
    var userName: String? = null
    var passWord: String? = null

    private var switch: Switch? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        //var sharedPref = SharedPref(this)

        //Checking state of preferences!
        /*if(sharedPref.getLightModeState() == true){
            setTheme(R.style.LightTheme)
        }

        else{
            setTheme(R.style.DefaultTheme)

        }

        switch = findViewById<Switch>(R.id.sw_switchTest)
        if(sharedPref.getLightModeState()==true){
            switch!!.isChecked = true

        }

        switch!!.setOnCheckedChangeListener{_, isChecked ->
            if(isChecked){
                sharedPref.setLightModeState(true)
                restartApplication()
            }

            else{
                sharedPref.setLightModeState(false)
                restartApplication()

            }


            }*/




        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val database = DatabaseHandler(this)
        val button = findViewById<Button>(R.id.btn_login)
        val button2 = findViewById<Button>(R.id.btn_createUserScreen)
        button.setOnClickListener {

            val userNameText: TextView = findViewById(R.id.plt_forLogin)
            val passwordText: TextView = findViewById(R.id.pass_forLogin)

            userName = userNameText.text.toString()
            passWord = passwordText.text.toString()

            if (userName!!.isEmpty() || passWord!!.isEmpty()) {
                Toast.makeText(this, "Username or password is empty!", Toast.LENGTH_SHORT).show()
            } else {
                if (database.checkUserLogIn(userName!!, passWord!!)) {
                    val intent = Intent(this, LoggedInActivity::class.java)
                    val user = database.getUser(userName!!, passWord!!)
                    if (user != null) {
                        intent.putExtra("Username", user.name)
                        intent.putExtra("Id", user.userID)
                        intent.putExtra("theme", user.theme)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this,"Could not get user from database", Toast.LENGTH_SHORT).show()
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Username or password is wrong!", Toast.LENGTH_SHORT).show()
                }
            }


        }

        button2.setOnClickListener{
            val intent = Intent(this, CreateUserActivity::class.java)
            startActivity(intent)

        }


    }

    /*private fun restartApplication(){
        val i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)
        finish()

    }*/
}
	