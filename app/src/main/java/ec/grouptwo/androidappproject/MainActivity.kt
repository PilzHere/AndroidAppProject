package ec.grouptwo.androidappproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ec.grouptwo.androidappproject.network.APIClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*
        För att söka efter ett spel
        val api = APIClient().SearchForGameUsingTitle("lord")
        */
    }
}
