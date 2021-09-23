package ec.grouptwo.androidappproject

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity

    class SearchGameActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_search_game)


        }
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.app_bar_menu, menu)
            return true
        }
    }


