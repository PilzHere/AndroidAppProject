package ec.grouptwo.androidappproject.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ec.grouptwo.androidappproject.SQLite.Query.FeedEntry.OWNED_GAMES_GAMEID
import ec.grouptwo.androidappproject.SQLite.Query.FeedEntry.OWNED_GAMES_USERID
import ec.grouptwo.androidappproject.SQLite.Query.FeedEntry.TABLE_GAMES
import ec.grouptwo.androidappproject.SQLite.Query.FeedEntry.TABLE_OWNED_GAMES
import ec.grouptwo.androidappproject.SQLite.Query.FeedEntry.TABLE_USERS
import java.io.File
import ec.grouptwo.androidappproject.SQLite.Query.FeedEntry.USERS_PASSWORD
import ec.grouptwo.androidappproject.SQLite.Query.FeedEntry.USERS_THEME
import ec.grouptwo.androidappproject.SQLite.Query.FeedEntry.USERS_USERNAME
import ec.grouptwo.androidappproject.user.User


class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, Query.FeedEntry.DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        createDB(db)
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        deleteUserByID(8, db)
    }

    private fun deleteUserByID(id: Int, db: SQLiteDatabase?) {
        db?.delete("users", "userID=$id", null)
    }

    fun checkDatabaseExists(): Boolean {
        val fileName = "./data/data/ec.grouptwo.androidappproject/databases/speljakt.db"
        val file = File(fileName)
        return file.exists()
    }


    fun checkUserLogIn(username: String, password: String): Boolean {
        val checkUserQuery =
            "SELECT * FROM $TABLE_USERS WHERE $USERS_USERNAME = '$username' AND $USERS_PASSWORD = '$password'"
        val db = this.readableDatabase

        val cursor = db.rawQuery(checkUserQuery, null)
        val valueFromCursor = cursor.count
        cursor.close()
        return valueFromCursor > 0
    }

    fun getUser(username: String, password: String): User? {
        var user: User
        val checkUserQuery =
            "SELECT * FROM $TABLE_USERS WHERE $USERS_USERNAME = '$username' AND $USERS_PASSWORD = '$password'"
        val db = this.readableDatabase

        val cursor = db.rawQuery(checkUserQuery, null)
        if (cursor.moveToFirst()) {
            val usersId = cursor.getInt(0)
            val usersName = cursor.getString(1)
            val usersTheme = cursor.getString(2)
            val usersPassword = "Nothing here"

            user = User(usersName, usersId.toString(), usersPassword, usersTheme)
            cursor.close()
            return user
        } else {
            cursor.close()
            return null   // Do nothing
        }

    }


    private fun createDB(db: SQLiteDatabase?) {
        val createTableUsers = "CREATE TABLE ${Query.FeedEntry.TABLE_USERS} (" +
                "${Query.FeedEntry.USERS_USERID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${Query.FeedEntry.USERS_USERNAME} VARCHAR(256), " +
                "${Query.FeedEntry.USERS_PASSWORD} VARCHAR(256), " +
                "${Query.FeedEntry.USERS_THEME} VARCHAR(256) ) "
        db?.execSQL(createTableUsers)

        val createTableGames = "CREATE TABLE ${Query.FeedEntry.TABLE_GAMES} (" +
                "${Query.FeedEntry.GAMES_GAMEID} VARCHAR(256) PRIMARY KEY, " +
                "${Query.FeedEntry.GAMES_NAME} VARCHAR(256), " +
                "${Query.FeedEntry.GAMES_PRICE} VARCHAR(256) )\n"
        db?.execSQL(createTableGames)

        db?.execSQL("PRAGMA foreign_keys=ON;")
        val createTableOwnedGames = "CREATE TABLE ${TABLE_OWNED_GAMES}(\n" +
                "  $OWNED_GAMES_USERID REFERENCES ${TABLE_USERS}(${OWNED_GAMES_USERID}),\n" +
                "  $OWNED_GAMES_GAMEID REFERENCES ${TABLE_GAMES}(${OWNED_GAMES_GAMEID})\n" +
                ");"
        db?.execSQL(createTableOwnedGames)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun addUser(name: String, password: String, theme: String): Boolean {

        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(USERS_USERNAME, name)
        cv.put(USERS_PASSWORD, password)
        cv.put(USERS_THEME, theme)

        val insert = db.insert(TABLE_USERS, null, cv)
        db.close()

        return insert != -1L
    }
}