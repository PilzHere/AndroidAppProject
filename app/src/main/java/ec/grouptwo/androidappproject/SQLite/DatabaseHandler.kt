package ec.grouptwo.androidappproject.SQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper




class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, Query.FeedEntry.DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?){
        createDB(db)
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
                "${Query.FeedEntry.GAMES_PRICE} VARCHAR(256) )"
        db?.execSQL(createTableGames)

        db?.setForeignKeyConstraintsEnabled(true)
        val createTableOwnedGames = "CREATE TABLE owned_games\n" +
                "owned_gamesID VARCHAR(256) PRIMARY KEY, " +
                "FOREIGN KEY(userID) REFERENCES users(userID), "
                "FOREIGN KEY(gameID) REFERENCES games(gameID) " +
                        ");"
        db?.execSQL(createTableOwnedGames)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}