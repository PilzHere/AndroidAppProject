package ec.grouptwo.androidappproject

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

import ec.grouptwo.androidappproject.SQLite.DatabaseHandler
import ec.grouptwo.androidappproject.SQLite.Query

class MainActivity : AppCompatActivity() {
    private lateinit var checkDatabaseButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = DatabaseHandler(this)
        val databaseHelper = database.writableDatabase // <------------------- This line causes the app to crash
        /*
        val values = ContentValues().apply{
            put(Query.FeedEntry.USERS_USERID, "U100")
            put(Query.FeedEntry.USERS_USERNAME, "jackCarrot")
            put(Query.FeedEntry.USERS_PASSWORD, "password")
            put(Query.FeedEntry.USERS_THEME, "Dark")
        }
        databaseHelper?.insert(Query.FeedEntry.TABLE_USERS, null, values)


        checkDatabaseButton = findViewById<Button>(R.id.checkDB)
        checkDatabaseButton.setOnClickListener{
            val toast = Toast.makeText(applicationContext, print(isTableExists(databaseHelper, Query.FeedEntry.TABLE_USERS)).toString(), Toast.LENGTH_LONG)
            toast.show()
        }
        isTableExists(databaseHelper, Query.FeedEntry.TABLE_USERS)

         */

    }
    fun isTableExists(database: SQLiteDatabase, tableName: String): Boolean {
        database.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '$tableName'", null)?.use {
            return it.count > 0
        } ?: return false
    }



}
