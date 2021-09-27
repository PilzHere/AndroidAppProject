package ec.grouptwo.androidappproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import ec.grouptwo.androidappproject.user.User


class LoggedInActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        val logOutButton = findViewById<Button>(R.id.btn_logOut)
        logOutButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

        }

        val toSearchButton = findViewById<Button>(R.id.btn_toSearch)
        toSearchButton.setOnClickListener {
            val intent = Intent(this, SearchGameActivity::class.java)
            forwardUser(intent)
            startActivity(intent)
        }

        val toAddOwnedGameButton = findViewById<Button>(R.id.btn_toAddOwnedGame)
        toAddOwnedGameButton.setOnClickListener {
            val intent = Intent(this, AddOwnedGameActivity::class.java)
            forwardUser(intent)
            startActivity(intent)
        }
    }
}
