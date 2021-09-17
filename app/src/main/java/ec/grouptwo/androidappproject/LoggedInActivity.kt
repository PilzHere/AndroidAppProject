package ec.grouptwo.androidappproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.TextView

class LoggedInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)



        val userName = intent.getStringExtra("USERNAME")
        val passWord = intent.getStringExtra("PASSWORD")

        //println("Hello " + userName + " your password is " + passWord)




        val logOutButton = findViewById<Button>(R.id.btn_logOut)
        logOutButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

        }

        val toSearchButton = findViewById<Button>(R.id.btn_toSearch)
                toSearchButton.setOnClickListener{
                    val intent = Intent(this, SearchGameActivity::class.java)

                    startActivity(intent)

                }
    }

}
