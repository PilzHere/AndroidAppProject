package ec.grouptwo.androidappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


var userName : String? = null
var passWord : String? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById<Button>(R.id.btn_login)
        button.setOnClickListener{

            val userNameText : TextView = findViewById(R.id.plt_forLogin)
            val passwordText : TextView = findViewById(R.id.pass_forLogin)

            userName = userNameText.text.toString()
            passWord = passwordText.text.toString()
            /*userName=R.id.plt_forLogin.toString()
            passWord=R.id.pass_forLogin.toString()*/

            val intent = Intent(this, LoggedInActivity::class.java)
            intent.putExtra("USERNAME", userName)
            intent.putExtra("PASSWORD", passWord)
            startActivity(intent);

        }
    }
}
