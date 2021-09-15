package ec.grouptwo.androidappproject

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = DatabaseHandler(this)
        //database.onCreate(SQLiteDatabase.create()) Vad gör man här??

    }

}
