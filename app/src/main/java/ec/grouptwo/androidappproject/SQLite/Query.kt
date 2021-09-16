package ec.grouptwo.androidappproject.SQLite

import android.provider.BaseColumns

object Query {
    // Table contents are grouped together in an anonymous object.
    object FeedEntry : BaseColumns {
        const val DATABASE_NAME ="speljakt.db"
        const val TABLE_USERS = "Users"
        const val USERS_USERID = "userID"
        const val USERS_USERNAME = "username"
        const val USERS_PASSWORD = "password"
        const val USERS_THEME = "theme"

        const val TABLE_GAMES ="games"
        const val GAMES_GAMEID = "gameID"
        const val GAMES_NAME = "name"
        const val GAMES_GENRE = "genre"
        const val GAMES_PRICE = "price"

        const val TABLE_OWNED_GAMES = "owned_games"
        const val OWNED_GAMES_USERID = "userID"
        const val OWNED_GAMES_GAMEID = "gameID"
    }
}