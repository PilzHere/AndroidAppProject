package ec.grouptwo.androidappproject.network

import android.util.Log
import ec.grouptwo.androidappproject.game.Game
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
TO FIND AND SAVE A GAME INTO A VARIABLE:

val apiClient = APIClient()
apiClient.SearchForGameUsingTitle("lord")

Change lord to whatever game you want to search for
After that you will be able to reach the gamelist using apiClient.gameList
*/

class APIClient {

    var gameList: List<Game>? = null


    private val retrofit = Retrofit.Builder()
        .baseUrl(RetrofitInterface.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun getApiInterface(): RetrofitInterface {
        val api = retrofit.create(RetrofitInterface::class.java)
        return api
    }

    fun SearchForGameUsingTitle(title: String) {
        val api: RetrofitInterface = APIClient().getApiInterface()
        val call = api.getGameByTitle(title)

        call?.enqueue(object : Callback<List<Game?>?> {
            override fun onResponse(
                call: Call<List<Game?>?>,
                response: Response<List<Game?>?>
            ) {
                (response.body() as List<Game>?)?.let { setNewGameList(it) }
            }

            override fun onFailure(call: Call<List<Game?>?>, t: Throwable) {
                Log.d("SearchForGameUsingTitle", "Did not work. Reason: " + t)
            }
        })
    }

    fun setNewGameList(newGameList: List<Game>) {
        gameList = newGameList
    }
}