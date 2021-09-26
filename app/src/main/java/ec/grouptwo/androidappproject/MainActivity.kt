package ec.grouptwo.androidappproject

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

import ec.grouptwo.androidappproject.SQLite.DatabaseHandler
import ec.grouptwo.androidappproject.SQLite.Query
import ec.grouptwo.androidappproject.user.User

class MainActivity : AppCompatActivity() {
    private lateinit var checkDatabaseButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = DatabaseHandler(this)
        val databaseHelper = database.writableDatabase

        Log.d("TAG", database.checkDatabaseExists().toString())
    }


}
