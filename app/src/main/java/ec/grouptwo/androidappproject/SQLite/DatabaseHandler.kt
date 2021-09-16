package ec.grouptwo.androidappproject.SQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper




class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, Query.FeedEntry.DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?){
        createDB(db)
        /*

        val createTableGames = "CREATE TABLE " + Query.FeedEntry.TABLE_GAMES +" (" +
                Query.FeedEntry.GAMES_GAMEID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                Query.FeedEntry.GAMES_NAME + " VARCHAR(256)," +
                Query.FeedEntry.GAMES_GENRE +" VARCHAR(256)," +
                Query.FeedEntry.GAMES_PRICE + " INT)"

        db?.execSQL(createTableGames)

        val createTableOwnedGames = "CREATE TABLE " +
                Query.FeedEntry.TABLE_OWNED_GAMES + " (" +
                Query.FeedEntry.OWNED_GAMES_USERID + "FOREIGN KEY(" + Query.FeedEntry.GAMES_GAMEID + ") REFERENCES " + Query.FeedEntry.TABLE_GAMES +
                Query.FeedEntry.OWNED_GAMES_GAMEID + "FOREIGN KEY(" + Query.FeedEntry.USERS_USERID + ") REFERENCES " + Query.FeedEntry.TABLE_USERS
        db?.execSQL(createTableOwnedGames)

         */
    }

    private fun createDB(db: SQLiteDatabase?){
        val createTableUsers = "CREATE TABLE ${Query.FeedEntry.TABLE_USERS} (" +
                "${Query.FeedEntry.USERS_USERID} VARCHAR(256) " +
                "${Query.FeedEntry.USERS_USERNAME} VARCHAR(256) " +
                "${Query.FeedEntry.USERS_PASSWORD} VARCHAR(256) " +
                "${Query.FeedEntry.USERS_THEME} VARCHAR(256) ) "
        db?.execSQL(createTableUsers)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}