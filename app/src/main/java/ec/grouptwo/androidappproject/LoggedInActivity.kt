package ec.grouptwo.androidappproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoggedInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)



        val userName = intent.getStringExtra("USERNAME")
        val passWord = intent.getStringExtra("PASSWORD")

        val newUserName = intent.getStringExtra("NEWUSERNAME")
        val newPassWord = intent.getStringExtra("NEWPASSWORD")

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

        val toAddOwnedGameButton = findViewById<Button>(R.id.btn_toAddOwnedGame)
        toAddOwnedGameButton.setOnClickListener{
            val intent = Intent(this, AddOwnedGameActivity::class.java)
            intent.putExtra("USERID", "1") // TODO: This should be an int from database.
            intent.putExtra("USERNAME", userName) // Do I need to do this here?

            startActivity(intent)
        }
    }

}
