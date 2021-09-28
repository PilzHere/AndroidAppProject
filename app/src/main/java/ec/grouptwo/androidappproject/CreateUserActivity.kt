package ec.grouptwo.androidappproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ec.grouptwo.androidappproject.SQLite.DatabaseHandler

class CreateUserActivity : AppCompatActivity() {

    var userName : String? = null
    var passWord : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        val button = findViewById<Button>(R.id.btn_createUser)
        button.setOnClickListener{
            val userNameText : TextView = findViewById(R.id.plt_createUserName)
            val passText : TextView = findViewById(R.id.pass_createUserPass)

            userName =  userNameText.text.toString()
            passWord = passText.text.toString()

            val intent = Intent(this, MainActivity::class.java)

             val DatabaseHandler1 = DatabaseHandler(baseContext)
            DatabaseHandler1.addUser(userName!!, passWord!!, "dark")

            startActivity(intent)




        }

    }
}