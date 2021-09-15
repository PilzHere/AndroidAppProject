package ec.grouptwo.androidappproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME ="speljakt"
val TABLE_USERS = "Users"
val USERS_USERID = "userID"
val USERS_USERNAME = "username"
val USERS_PASSWORD = "password"
val USERS_THEME = "theme"

val TABLE_GAMES ="games"
val GAMES_GAMEID = "gameID"
val GAMES_NAME = "name"
val GAMES_GENRE = "genre"
val GAMES_PRICE = "price"

val TABLE_OWNED_GAMES = "owned_games"
val OWNED_GAMES_USERID = "userID"
val OWNED_GAMES_GAMEID = "gameID"


class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?){
        val createTableUsers = "CREATE TABLE " + TABLE_USERS +" (" +
                USERS_USERID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                USERS_USERNAME + " VARCHAR(256)," +
                USERS_PASSWORD +" VARCHAR(256)," +
                USERS_THEME + " VARCHAR(256))"

        db?.execSQL(createTableUsers)

        val createTableGames = "CREATE TABLE " + TABLE_GAMES +" (" +
                GAMES_GAMEID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                GAMES_NAME + " VARCHAR(256)," +
                GAMES_GENRE +" VARCHAR(256)," +
                GAMES_PRICE + " INT)"

        db?.execSQL(createTableGames)

        val createTableOwnedGames = "CREATE TABLE " + TABLE_OWNED_GAMES + " (" +
            OWNED_GAMES_USERID + "FOREIGN KEY(" + GAMES_GAMEID + ") REFERENCES " + TABLE_GAMES +
                OWNED_GAMES_GAMEID + "FOREIGN KEY(" + USERS_USERID + ") REFERENCES " + TABLE_USERS
        db?.execSQL(createTableOwnedGames)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}