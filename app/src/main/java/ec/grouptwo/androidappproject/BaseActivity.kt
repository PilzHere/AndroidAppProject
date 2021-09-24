package ec.grouptwo.androidappproject

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        supportActionBar?.setLogo(R.drawable.ic_logo)
        supportActionBar?.setDisplayUseLogoEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val profileDialog = Dialog(this)
        when (item.itemId) {
            android.R.id.home -> onBackPressed()    // To make back button work

            R.id.miTheme -> {
                // TODO: Add functions to this method
                println("miTheme is clicked")
            }

            R.id.miProfile -> {
                profileDialog.setContentView(R.layout.popup_profile)
                profileDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                val tvClose: TextView = profileDialog.findViewById(R.id.tvProfileClose)
                val tvLogout: TextView = profileDialog.findViewById(R.id.tvProfileLogoutBtn)
                setUserProfile(profileDialog)

                tvClose.setOnClickListener {
                    profileDialog.dismiss()
                }
                tvLogout.setOnClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                profileDialog.show()
            }

        }
        return true
    }

    fun setUserProfile(profileDialog: Dialog) {
        val tvName: TextView = profileDialog.findViewById(R.id.tvProfileName)
        val tvCityCountry: TextView = profileDialog.findViewById(R.id.tvProfileCityCountry)
        val tvAmountGames: TextView = profileDialog.findViewById(R.id.tvProfieAmountGames)
        val tvAmountFriends: TextView = profileDialog.findViewById(R.id.tvProfileAmountFriends)
        val tvAmountDeals: TextView = profileDialog.findViewById(R.id.tvProfileAmountDeals)

        tvName.text = "Firstname Lastname"
        tvCityCountry.text = "Malmö, Sweden"
        tvAmountDeals.text ="11"
        tvAmountFriends.text ="5"
        tvAmountGames.text ="15"
    }
}