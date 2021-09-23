package ec.grouptwo.androidappproject

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        getSupportActionBar()?.setLogo(R.drawable.ic_logo)
        getSupportActionBar()?.setDisplayUseLogoEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        return true
    }
}