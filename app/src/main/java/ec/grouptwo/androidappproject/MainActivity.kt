package ec.grouptwo.androidappproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ec.grouptwo.androidappproject.SQLite.DatabaseHandler
import ec.grouptwo.androidappproject.SQLite.Query
import ec.grouptwo.androidappproject.user.User


class MainActivity : AppCompatActivity() {
    var userName: String? = null
    var passWord: String? = null

    private lateinit var checkDatabaseButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val database = DatabaseHandler(this)
        val databaseHelper = database.writableDatabase


        val button = findViewById<Button>(R.id.btn_login)
        button.setOnClickListener {

            val userNameText: TextView = findViewById(R.id.plt_forLogin)
            val passwordText: TextView = findViewById(R.id.pass_forLogin)

            userName = userNameText.text.toString()
            passWord = passwordText.text.toString()
            /*userName=R.id.plt_forLogin.toString()
            passWord=R.id.pass_forLogin.toString()*/

            val intent = Intent(this, LoggedInActivity::class.java)
            intent.putExtra("USERID", "1") // TODO: This should be an int from database.
            intent.putExtra("USERNAME", userName)
            intent.putExtra("PASSWORD", passWord)
            startActivity(intent)
        }
    }
}
	