package ec.grouptwo.androidappproject

import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import ec.grouptwo.androidappproject.SQLite.DatabaseHandler
import ec.grouptwo.androidappproject.SQLite.Query
import ec.grouptwo.androidappproject.game.Game
import ec.grouptwo.androidappproject.network.APIClient

class AddOwnedGameActivity : BaseActivity() {
    private lateinit var textGameDatabaseMessage: TextView
    private lateinit var textGameTitleFound: TextView
    private lateinit var etGameTitle: EditText

    private var gameFound = false
    private var currentGame: Game? = null
    private var currentGameId = ""
    private lateinit var  iv_thumbnail : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_owned_game)

        val db = DatabaseHandler(this) // Is this needed in each class?
        iv_thumbnail = findViewById<ImageView>(R.id.iv_thumbnail)
        etGameTitle = findViewById(R.id.editText_game_title)
        textGameTitleFound = findViewById(R.id.textv_title_of_game_found)
        val buttonAddGameToList: Button = findViewById(R.id.btn_add_game_to_list)

        textGameDatabaseMessage = findViewById(R.id.textv_game_database_message)

        val apiClient = APIClient()

        etGameTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Not used
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Not used
            }

            override fun afterTextChanged(p0: Editable?) {
                searchForGameTitleWithAPI(apiClient, p0)
            }
        })

        buttonAddGameToList.setOnClickListener {
            if (etGameTitle.text.isNotEmpty() && gameFound && currentGameId.isNotEmpty() && currentGame != null) {
                val userId = user.userID
                val gameId = currentGameId

                if (!gameAlreadyOwnedbyUser(db, userId, gameId)) {
                    putGameIntoDatabase(db, userId, gameId)
                }
            } else {
                textGameDatabaseMessage.setText("There was an error. Search again.")
                textGameDatabaseMessage.visibility = View.VISIBLE
            }
        }
    }

    private fun searchForGameTitleWithAPI(apiClient: APIClient, p0: Editable?) {
        if (etGameTitle.text.isNotEmpty()) {
            apiClient.SearchForGameUsingTitle(p0.toString())

            if (apiClient.gameList?.size != 0) {
                currentGame = apiClient.gameList?.get(0)
                textGameTitleFound.setText(currentGame?.external)
                currentGameId = currentGame?.gameID ?: currentGameId // Weird.
                gameFound = true



                if (currentGame != null) {
                    if (currentGame?.thumb?.isNotEmpty() == true) {
                        iv_thumbnail.load(currentGame?.thumb) {
                            (CircleCropTransformation())

                        }
                    }
                }
            } else {
                gameFound = false
                textGameTitleFound.setText("Game not found.")
                currentGame = null
                currentGameId = ""
            }
        } else {
            textGameTitleFound.setText("Search for a game title.")
        }
    }

    private fun putGameIntoDatabase(db: DatabaseHandler, userId: String, gameId: String) {
        val values = ContentValues().apply {
            put(Query.FeedEntry.OWNED_GAMES_USERID, userId)
            put(Query.FeedEntry.OWNED_GAMES_GAMEID, gameId)
        }

        val dbWriter = db.writableDatabase
        val newRowId = dbWriter.insert(Query.FeedEntry.TABLE_OWNED_GAMES, null, values)

        if (newRowId != -1L) { // Check for error return signal.
            textGameDatabaseMessage.setText("Game was added to your list.")
            textGameDatabaseMessage.visibility = View.VISIBLE
        } else {
            textGameDatabaseMessage.setText("Game was NOT added to your list: (db-error)")
            textGameDatabaseMessage.visibility = View.VISIBLE
        }
    }

    private fun gameAlreadyOwnedbyUser(
        db: DatabaseHandler,
        userId: String,
        gameId: String
    ): Boolean {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(Query.FeedEntry.OWNED_GAMES_USERID, Query.FeedEntry.OWNED_GAMES_GAMEID)

        // Filter results WHERE "title" = 'My Title'
        val selection = "${Query.FeedEntry.OWNED_GAMES_USERID} = ?"
        val selectionArgs = arrayOf(userId)

        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${Query.FeedEntry.OWNED_GAMES_GAMEID} DESC"

        val dbReader = db.readableDatabase
        val cursor = dbReader.query(
                Query.FeedEntry.TABLE_OWNED_GAMES,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        )

        // check if gameid and userid already matches
        if (cursor.count > 0) {
            cursor.moveToFirst()

            while (!cursor.isAfterLast) {
                val gameId2 = cursor.getString(cursor.getColumnIndex("gameID"))

                if (gameId2 == gameId) {
                    textGameDatabaseMessage.setText("You already own this game =)")
                    textGameDatabaseMessage.visibility = View.VISIBLE

                    return true
                }

                cursor.moveToNext()
            }
        }

        return false
    }
}