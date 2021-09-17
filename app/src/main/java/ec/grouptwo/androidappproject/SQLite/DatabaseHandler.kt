package ec.grouptwo.androidappproject.SQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ec.grouptwo.androidappproject.SQLite.Query.FeedEntry.OWNED_GAMES_GAMEID
import ec.grouptwo.androidappproject.SQLite.Query.FeedEntry.OWNED_GAMES_USERID
import ec.grouptwo.androidappproject.SQLite.Query.FeedEntry.TABLE_GAMES
import ec.grouptwo.androidappproject.SQLite.Query.FeedEntry.TABLE_OWNED_GAMES
import ec.grouptwo.androidappproject.SQLite.Query.FeedEntry.TABLE_USERS


class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, Query.FeedEntry.DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?){
        createDB(db)
        createForeignKeyDB(db)
    }

    private fun createDB(db: SQLiteDatabase?){
        val createTableUsers = "CREATE TABLE ${Query.FeedEntry.TABLE_USERS} (" +
                "${Query.FeedEntry.USERS_USERID} VARCHAR(256) PRIMARY KEY, " +
                "${Query.FeedEntry.USERS_USERNAME} VARCHAR(256), " +
                "${Query.FeedEntry.USERS_PASSWORD} VARCHAR(256), " +
                "${Query.FeedEntry.USERS_THEME} VARCHAR(256) ) "
        db?.execSQL(createTableUsers)

        val createTableGames = "CREATE TABLE ${Query.FeedEntry.TABLE_GAMES} (" +
                "${Query.FeedEntry.GAMES_GAMEID} VARCHAR(256) PRIMARY KEY, " +
                "${Query.FeedEntry.GAMES_NAME} VARCHAR(256), " +
                "${Query.FeedEntry.GAMES_PRICE} VARCHAR(256) )\n"
        db?.execSQL(createTableGames)

    }
    private fun createForeignKeyDB(db: SQLiteDatabase?){
        db?.execSQL("PRAGMA foreign_keys=ON;")
        val createTableOwnedGames = "CREATE TABLE ${TABLE_OWNED_GAMES}(\n" +
                "  $OWNED_GAMES_USERID REFERENCES ${TABLE_USERS}(${OWNED_GAMES_USERID}),\n" +
                "  $OWNED_GAMES_GAMEID REFERENCES ${TABLE_GAMES}(${OWNED_GAMES_GAMEID})\n" +
                ");"
        db?.execSQL(createTableOwnedGames)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}